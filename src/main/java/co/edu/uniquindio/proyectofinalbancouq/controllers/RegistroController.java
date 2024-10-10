package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.ArchivoUtil;
import co.edu.uniquindio.proyectofinalbancouq.util.LogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.LinkedList;

public class RegistroController {

    public TextField nombreField;
    public TextField idField;
    public TextField correoField;
    public TextField direccionField;
    public TextField telefonoField;
    public PasswordField contrasenaField;
    public Label etiquetaMensaje;

    private static LinkedList<Usuario> usuariosRegistrados = new LinkedList<>();

    public void registrarUsuario(ActionEvent event) {
        String nombre = nombreField.getText();
        String id = idField.getText();
        String correo = correoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String contrasena = contrasenaField.getText();

        if (nombre.isEmpty() || id.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            etiquetaMensaje.setText("Por favor, completa todos los campos.");
            return;
        }

        try {
            Usuario nuevoUsuario = new Usuario(id, nombre, correo, direccion, telefono, contrasena);
            usuariosRegistrados.add(nuevoUsuario);
            etiquetaMensaje.setText("Usuario registrado: " + nuevoUsuario);

            // Guardar los usuarios en el archivo de texto
            ArchivoUtil.guardarUsuarios(usuariosRegistrados);

            // Registrar en el archivo Log
            LogUtil.registrarAccion(nuevoUsuario.getId(), "Nuevo usuario registrado");

        } catch (Exception e) {
            etiquetaMensaje.setText("Error al registrar el usuario: " + e.getMessage());
            // Registrar la excepción en el archivo Log
            LogUtil.registrarExcepcion(e);
        }
    }

    public static LinkedList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void volverALogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }
}
