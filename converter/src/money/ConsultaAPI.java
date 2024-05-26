package money;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ConsultaAPI {
    private String apiKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ConsultaAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String desdeCodigo, String haciaCodigo) throws Exception {
        if (!esStringValido(desdeCodigo) || !esStringValido(haciaCodigo)) {
            throw new Exception("Los códigos deben ser cadenas válidas.");
        }
        if (!esCadenaTexto(desdeCodigo) || !esCadenaTexto(haciaCodigo)) {
            throw new Exception("Los códigos deben ser cadenas de texto.");
        }

        String url = BASE_URL + apiKey + "/latest/" + desdeCodigo.toUpperCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JSONObject datos = new JSONObject(response.body());
            if (datos.getString("result").equals("success")) {
                JSONObject tasas = datos.getJSONObject("conversion_rates");
                if (tasas.has(haciaCodigo.toUpperCase())) {
                    return tasas.getDouble(haciaCodigo.toUpperCase());
                } else {
                    throw new Exception("No se encontró tasa de cambio para " + haciaCodigo);
                }
            } else {
                throw new Exception("Error en la respuesta de la API");
            }
        } else {
            throw new Exception("Error en la solicitud HTTP: " + response.statusCode());
        }
    }
    private boolean esStringValido(String str) {
        return str != null && !str.isEmpty();
    }
    private boolean esCadenaTexto(String str) {
        if (!esStringValido(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }


    public Moneda convertir(Moneda monedaDesde, String codigoHacia) throws Exception {
        double tasa = obtenerTasa(monedaDesde.getCodigo(), codigoHacia);
        double cantidadConvertida = monedaDesde.getCantidad() * tasa;
        return new Moneda(codigoHacia, cantidadConvertida);
    }
}
