import money.ConsultaAPI;
import money.Moneda;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.util.Scanner;
import java.text.NumberFormat;

public class PrincipalClaseMoneda {
    private ConsultaAPI api;

    public PrincipalClaseMoneda(String apiKey) {
        this.api = new ConsultaAPI(apiKey);
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        JTextField textField = new JTextField();

        // MONEDA DE ORIGEN
        JOptionPane.showConfirmDialog(null, textField, "Ingrese el código de la moneda de origen", JOptionPane.DEFAULT_OPTION);
        String desdeCodigo = textField.getText();

        //CANTIDAD MONEDA DE ORIGEN
        JFormattedTextField doubleField = new JFormattedTextField(NumberFormat.getNumberInstance());
        NumberFormatter formatter = (NumberFormatter) doubleField.getFormatter();
        formatter.setValueClass(Double.class);
        JOptionPane.showConfirmDialog(null, doubleField, "Ingrese la cantidad de " + desdeCodigo + ": ", JOptionPane.DEFAULT_OPTION);
        double cantidad = (Double) doubleField.getValue();;

        //MONEDA DE DESTINO
        JOptionPane.showConfirmDialog(null, textField, "Ingrese el código de la moneda de destino: ", JOptionPane.DEFAULT_OPTION);
        String haciaCodigo = textField.getText();

        Moneda monedaOrigen = new Moneda(desdeCodigo, cantidad);

        try{
            Moneda monedaDestino = api.convertir(monedaOrigen, haciaCodigo);
            System.out.println(monedaOrigen + " es igual a " + monedaDestino);
            JOptionPane.showMessageDialog(null,monedaOrigen + " es igual a " + monedaDestino );
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static void main(String[] args) {
        String apiKey = "ae16f90f181cf4aa80fca142";
        PrincipalClaseMoneda moneda = new PrincipalClaseMoneda(apiKey);
        moneda.ejecutar();
    }
}
