package co.edu.uniquindio.proyectofinalbancouq.util;


import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    private static final String USUARIO_PATH = "C:/td/persistencia/archivos/usuarios.txt";
    private static final String RESPALDO_PATH = "C:/td/persistencia/archivos/respaldo/";


    public static void respaldarArchivo(String archivoOriginal) {
        File archivo = new File(archivoOriginal);

        if (archivo.exists()) {
            try {
                // Obtener el nombre del archivo sin la ruta
                String nombreArchivo = archivo.getName();

                // Generar el nombre del archivo de respaldo con timestamp
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String nombreRespaldo = nombreArchivo.replace(".txt", "_respaldo_" + timestamp + ".txt");

                // Ruta del archivo de respaldo
                File archivoRespaldo = new File(RESPALDO_PATH + nombreRespaldo);

                // Crear la carpeta de respaldo si no existe
                if (!archivoRespaldo.getParentFile().exists()) {
                    archivoRespaldo.getParentFile().mkdirs();
                }

                // Copiar el archivo original al archivo de respaldo
                Files.copy(archivo.toPath(), archivoRespaldo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Respaldo realizado: " + archivoRespaldo.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al realizar el respaldo del archivo: " + archivoOriginal);
            }
        } else {
            System.out.println("El archivo original no existe: " + archivoOriginal);
        }
    }


    private static void verificarCarpetaArchivos() {
        File carpeta = new File("C:/td/persistencia/archivos");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y todas las necesarias
        }
    }


    // Guardar usuarios en el archivo
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIO_PATH))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getId() + "@@" + usuario.getNombreCompleto() + "@@" +
                        usuario.getCorreo() + "@@" + usuario.getDireccion() + "@@" +
                        usuario.getTelefono() + "@@" + usuario.getContraseña() + "\n");
            }
            ArchivoUtil.respaldarArchivo(USUARIO_PATH);
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIO_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Verificar que la línea no esté vacía o mal formateada
                if (linea.trim().isEmpty()) {
                    continue; // Saltar líneas vacías
                }

                String[] datos = linea.split("@@");

                // Verificar que el número de elementos sea el esperado (6 en este caso)
                if (datos.length == 6) {
                    // Crear el objeto Usuario con los datos
                    Usuario usuario = new Usuario(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                    usuarios.add(usuario);
                } else {
                    // Manejar el caso de una línea mal formateada (opcionalmente puedes registrar el error)
                    System.out.println("Línea con formato incorrecto: " + linea);
                    // Puedes registrar esto en el log también:
                    LogUtil.registrarAccion("Error de formato", "Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
        return usuarios;
    }
}


