package co.edu.uniquindio.proyectofinalbancouq.util;


import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    private static final String USUARIO_PATH = "C:/td/persistencia/archivos/usuarios.txt";

    // Guardar usuarios en el archivo
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIO_PATH))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getId() + "@@" + usuario.getNombreCompleto() + "@@" +
                        usuario.getCorreo() + "@@" + usuario.getDireccion() + "@@" +
                        usuario.getTelefono() + "@@" + usuario.getContraseña() + "\n");
            }
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
