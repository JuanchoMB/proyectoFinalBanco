package co.edu.uniquindio.proyectofinalbancouq.util;

import co.edu.uniquindio.proyectofinalbancouq.model.Transaccion;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaccionUtil {

    private static final String TRANSACCION_PATH = "C:/td/persistencia/transacciones.txt";


    // Guardar una nueva transacción
    public static void guardarTransaccion(Transaccion transaccion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACCION_PATH, true))) {
            writer.write(transaccion.getIdTransaccion() + "@@" + transaccion.getCantidad() + "@@" +
                    transaccion.getTipo() + "@@" + transaccion.getFecha() + "\n");

        }

        catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    // Realizar respaldo

    }

