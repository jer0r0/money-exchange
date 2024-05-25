import javax.swing.JOptionPane;

public class CurrencyConverter {
    public static void main(String[] args) {
        // Opciones disponibles para el usuario
        String[] options = {"USD a EUR", "EUR a USD", "USD a GBP", "GBP a USD"};

        // Mostrar el cuadro de diálogo con las opciones
        String choice = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la conversión de moneda que desea realizar:",
                "Conversor de Moneda",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice != null) {
            // Pedir el monto a convertir
            String amountStr = JOptionPane.showInputDialog("Ingrese el monto que desea convertir:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    double result = 0;
                    String resultMessage = "";

                    // Realizar la conversión según la opción seleccionada
                    switch (choice) {
                        case "USD a EUR":
                            result = amount * 0.85; // Ejemplo de tasa de cambio
                            resultMessage = String.format("USD %.2f es EUR %.2f", amount, result);
                            break;
                        case "EUR a USD":
                            result = amount * 1.18; // Ejemplo de tasa de cambio
                            resultMessage = String.format("EUR %.2f es USD %.2f", amount, result);
                            break;
                        case "USD a GBP":
                            result = amount * 0.75; // Ejemplo de tasa de cambio
                            resultMessage = String.format("USD %.2f es GBP %.2f", amount, result);
                            break;
                        case "GBP a USD":
                            result = amount * 1.33; // Ejemplo de tasa de cambio
                            resultMessage = String.format("GBP %.2f es USD %.2f", amount, result);
                            break;
                        default:
                            resultMessage = "Conversión no reconocida.";
                            break;
                    }

                    // Mostrar el resultado de la conversión
                    JOptionPane.showMessageDialog(null, resultMessage);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ingresó ningún monto.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna conversión.");
        }
    }
}
