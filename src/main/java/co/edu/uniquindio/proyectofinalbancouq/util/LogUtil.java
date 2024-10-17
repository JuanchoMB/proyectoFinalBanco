package co.edu.uniquindio.proyectofinalbancouq.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogUtil {

    private static final String LOG_PATH = "C:/td/persistencia/logs/log.txt";

    // Metodo para asegurarse de que la carpeta exista
    private static void verificarCarpetaLogs() {
        File carpeta = new File("C:/td/persistencia/logs");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y todas las necesarias
        }
    }

    public static void registrarAccion(String usuario, String accion) {
        verificarCarpetaLogs(); // Verifica y crea la carpeta si no existe
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(LocalDateTime.now() + " - Usuario: " + usuario + " - Acción: " + accion + "\n");
        } catch (IOException e) {
            registrarExcepcion(e);
        }
    }

    public static void registrarExcepcion(Exception e) {
        verificarCarpetaLogs(); // Verifica y crea la carpeta si no existe
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(LocalDateTime.now() + " - Excepción: " + e.getMessage() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
