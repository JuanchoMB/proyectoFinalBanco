package co.edu.uniquindio.proyectofinalbancouq;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import persistencia.Persistencia;

import java.io.IOException;
import java.util.LinkedList;

public class RegistroController {

    // Campos de texto para los datos del usuario
    public TextField nombreField;
    public TextField idField;
    public TextField correoField;
    public TextField direccionField;
    public TextField telefonoField;
    public PasswordField contrasenaField;
    public Label etiquetaMensaje;

    // Lista estática para almacenar los usuarios registrados
    private static LinkedList<Usuario> usuariosRegistrados = new LinkedList<>();

    // Método para registrar un usuario
    public void registrarUsuario(ActionEvent event) {
        String nombre = nombreField.getText();
        String id = idField.getText();
        String correo = correoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String contrasena = contrasenaField.getText();

        // Validar los campos
        if (nombre.isEmpty() || id.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            etiquetaMensaje.setText("Por favor, completa todos los campos."); // Mostrar mensaje
            return;
        }

        // Crear un nuevo usuario y agregarlo a la lista
        try {
            Usuario nuevoUsuario = new Usuario(id, nombre, correo, direccion, telefono, contrasena);
            usuariosRegistrados.add(nuevoUsuario);

            etiquetaMensaje.setText("Usuario registrado: " + nuevoUsuario); // Mostrar mensaje

            Persistencia persistencia = new Persistencia();
            try {
                persistencia.guardarUsuario(Usuario);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

        } catch (Exception e) {
            etiquetaMensaje.setText("Error al registrar el usuario: " + e.getMessage()); // Mostrar mensaje
            e.printStackTrace();
        }
    }

    // Método para volver a la pantalla de inicio de sesión
    public void volverALogin(ActionEvent event) {
        try {
            // Cargar la vista de inicio de sesión
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent loginRoot = loader.load();

            // Obtener el stage actual y cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.show();

        } catch (IOException e) {
            etiquetaMensaje.setText("Error al cargar la vista de inicio de sesión."); // Mostrar mensaje
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        nombreField.clear();
        idField.clear();
        correoField.clear();
        direccionField.clear();
        telefonoField.clear();
        contrasenaField.clear();
    }

    // Método para obtener la lista de usuarios registrados (opcional)
    public static LinkedList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }
    public void addUsuario( Usuario usuario) {

        System.out.println("Usuario agregado");

        Persistencia persistencia = new Persistencia();
        try {
            persistencia.guardarEmpleados(empresa.getEmpleados());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
