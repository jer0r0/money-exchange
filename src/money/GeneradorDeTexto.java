package money;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class GeneradorDeTexto {
    public void guardarTxt(List<Operacion> operaciones) {
        // Mostrar el cuadro de diálogo de selección de archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");
        fileChooser.setSelectedFile(new java.io.File("archivo.txt")); // Nombre de archivo por defecto
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener la ruta del archivo seleccionado por el usuario
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            escribirListaEnArchivo(operaciones, rutaArchivo);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario canceló la operación.");
            System.out.println("El usuario canceló la operación.");
        }
    }

    public void escribirListaEnArchivo(List<Operacion> operaciones, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("Operaciones Realizadas:\n\n");
            for (Operacion elemento : operaciones) {
                writer.write( elemento.toString());
                writer.newLine(); // Agregar un salto de línea después de cada elemento
            }
            JOptionPane.showMessageDialog(null, "El archivo " + rutaArchivo + " se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("El archivo " + rutaArchivo + " se ha creado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
