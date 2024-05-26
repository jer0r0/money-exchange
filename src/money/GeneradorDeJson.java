package money;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorDeJson{
    public void guardarJson(List<Operacion> operaciones) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");
        fileChooser.setSelectedFile(new java.io.File("archivo.json"));
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            escribirListaEnJson(operaciones, rutaArchivo);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario canceló la operación.");
            System.out.println("El usuario canceló la operación.");
        }
    }

    public void escribirListaEnJson(List<Operacion> operaciones, String rutaArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OperacionesData data = new OperacionesData(operaciones);

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(data, writer);
            JOptionPane.showMessageDialog(null, "El archivo " + rutaArchivo + " se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("El archivo " + rutaArchivo + " se ha creado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    class OperacionesData {
        List<Operacion> operaciones;

        public OperacionesData(List<Operacion> operaciones) {
            this.operaciones = operaciones;
        }
    }

}
