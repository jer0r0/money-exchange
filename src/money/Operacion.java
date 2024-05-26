package money;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operacion {
    private Moneda monedaOrigen;
    private Moneda monedaDestino;
    private static int contadorid = 0;
    private String hora;
    private int id;


    public Operacion(Moneda monedaOrigen, Moneda monedaDestino) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.id=  ++contadorid;
        this.hora = obtenerHoraAactual();
    }

    private String obtenerHoraAactual(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActual = ahora.format(formatter);
        return horaActual;
    }

    @Override
    public String toString() {
        return
                id +"). "+
                monedaOrigen + " es igual a "+
                monedaDestino +"    Hora: "+hora
                ;
    }
}
