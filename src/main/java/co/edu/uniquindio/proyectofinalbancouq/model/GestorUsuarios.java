package co.edu.uniquindio.proyectofinalbancouq.model;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public boolean registrar(String id, String nombreCompleto, String correo, String direccion, String telefono, String contraseña) {
        if (usuarios.containsKey(id)) {
            return false; // El ID ya está registrado
        }
        Usuario nuevoUsuario = new Usuario(id, nombreCompleto, correo, direccion, telefono, contraseña);
        usuarios.put(id, nuevoUsuario);
        return true;
    }

    public Usuario iniciarSesion(String id, String contraseña) {
        Usuario usuario = usuarios.get(id);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            return usuario; // Inicio de sesión exitoso
        }
        return null; // Inicio de sesión fallido
    }

}