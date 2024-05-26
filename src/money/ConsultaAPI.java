package money;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import javax.swing.*;

public class ConsultaAPI {
    private String apiKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ConsultaAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String desdeCodigo, String haciaCodigo) throws Exception {
        String url = BASE_URL + apiKey + "/latest/" + desdeCodigo.toUpperCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        JSONObject datos = new JSONObject(response.body());
        if (datos.getString("result").equals("success")) {
            JSONObject tasas = datos.getJSONObject("conversion_rates");
            if (tasas.has(haciaCodigo.toUpperCase())) {
                return tasas.getDouble(haciaCodigo.toUpperCase());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró tasa de cambio para " + haciaCodigo);
                throw new Exception("No se encontró tasa de cambio para " + haciaCodigo);
            }
        } else if (datos.getString("result").equals("error")) {
            JOptionPane.showMessageDialog(null, "No se encontró la moneda: " + desdeCodigo);
            throw new Exception("No se encontró la moneda: " + desdeCodigo);
        } else {
            JOptionPane.showMessageDialog(null,"Error en la respuesta de la API");
            throw new Exception("Error en la respuesta de la API");
        }
    }

    public Moneda convertir(Moneda monedaDesde, String codigoHacia) throws Exception {
        double tasa = obtenerTasa(monedaDesde.getCodigo(), codigoHacia);
        double cantidadConvertida = monedaDesde.getCantidad() * tasa;
        return new Moneda(codigoHacia, cantidadConvertida);
    }
}
