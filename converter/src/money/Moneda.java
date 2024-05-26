package money;

public class Moneda {
    private String codigo;
    private double cantidad;

    public Moneda(String codigo, double cantidad) {
        this.codigo = codigo.toUpperCase();
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return cantidad + " " + codigo;
    }
}
