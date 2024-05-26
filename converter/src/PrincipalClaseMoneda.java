import money.ConsultaAPI;
import money.Moneda;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.Scanner;
import java.text.NumberFormat;

public class PrincipalClaseMoneda {
    private ConsultaAPI api;

    public PrincipalClaseMoneda(String apiKey) {
        this.api = new ConsultaAPI(apiKey);
    }
    ImageIcon originalIcon = new ImageIcon("src/media/logosimple.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon icon = new ImageIcon(scaledImage);

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        JTextField textField = new JTextField();
        // Cargar la imagen original y escalarla


        while(true){
            // MONEDA DE ORIGEN
            String desdeCodigo = obtenerCodigoMoneda("Ingrese el código de la moneda de origen");

            // CANTIDAD MONEDA DE ORIGEN
            double cantidad = obtenerCantidad("Ingrese la cantidad de " + desdeCodigo + ": ");

            // MONEDA DE DESTINO
            String haciaCodigo = obtenerCodigoMoneda("Ingrese el código de la moneda de destino: ");

            Moneda monedaOrigen = new Moneda(desdeCodigo, cantidad);

            try{
                Moneda monedaDestino = api.convertir(monedaOrigen, haciaCodigo);
                System.out.println(monedaOrigen + " es igual a " + monedaDestino);
                JOptionPane.showMessageDialog(null,monedaOrigen + " es igual a " + monedaDestino,"",0,icon );
                int option = JOptionPane.showOptionDialog(
                        null,
                        "¿Desea convertir otra moneda?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        icon, //ICONO
                        new Object[]{"Sí", "No"},
                        "Sí"
                );
                if(option==JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(null, "Operación cancelada.","",0,icon);
                    break;
                }
            }catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            scanner.close();
        }

    }
    private String obtenerCodigoMoneda(String mensaje) {
        JTextField textField = new JTextField();
        int option = JOptionPane.showConfirmDialog(null, textField, mensaje, JOptionPane.DEFAULT_OPTION,0,icon);
        if (option == JOptionPane.OK_OPTION) {
            String codigo = textField.getText();
            if (!esCadenaTexto(codigo)) {
                JOptionPane.showMessageDialog(null, "El código de moneda debe ser una cadena válida y no estar vacío.");
                return obtenerCodigoMoneda(mensaje); // Vuelve a solicitar el código
            }
            return codigo;
        } else {
            System.exit(0); // Salir del programa si el usuario cancela
            return null; // Esto solo se ejecutará si el usuario cancela
        }
    }

    private double obtenerCantidad(String mensaje) {
        JFormattedTextField doubleField = new JFormattedTextField(NumberFormat.getNumberInstance());
        NumberFormatter formatter = (NumberFormatter) doubleField.getFormatter();
        formatter.setValueClass(Double.class);
        int option = JOptionPane.showConfirmDialog(null, doubleField, mensaje, JOptionPane.DEFAULT_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Double cantidad = (Double) doubleField.getValue();
            if (cantidad == null || cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un número positivo.");
                return obtenerCantidad(mensaje); // Vuelve a solicitar la cantidad
            }
            return cantidad;
        } else {
            System.exit(0); // Salir del programa si el usuario cancela
            return 0; // Esto solo se ejecutará si el usuario cancela
        }
    }
    private boolean esCodigoValido(String codigo) {
        return codigo != null && !codigo.isEmpty();
    }
    private boolean esCadenaTexto(String str) {
        if (!esCodigoValido(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String apiKey = "ae16f90f181cf4aa80fca142";
        PrincipalClaseMoneda moneda = new PrincipalClaseMoneda(apiKey);
        moneda.ejecutar();
    }
}
