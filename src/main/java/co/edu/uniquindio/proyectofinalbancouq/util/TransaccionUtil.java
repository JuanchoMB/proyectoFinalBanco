package co.edu.uniquindio.proyectofinalbancouq.util;

import co.edu.uniquindio.proyectofinalbancouq.model.Transaccion;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaccionUtil {

    private static final String TRANSACCION_PATH = "C:/td/persistencia/transacciones.txt";
    private static final String RESPALDO_PATH = "C:/td/persistencia/respaldo/transacciones_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";

    // Guardar una nueva transacción
    public static void guardarTransaccion(Transaccion transaccion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACCION_PATH, true))) {
            writer.write(transaccion.getIdTransaccion() + "@@" + transaccion.getCantidad() + "@@" +
                    transaccion.getTipo() + "@@" + transaccion.getFecha() + "\n");
            respaldarTransaccion();
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    // Realizar respaldo
    private static void respaldarTransaccion() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACCION_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(RESPALDO_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea + "\n");
            }
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }
}
