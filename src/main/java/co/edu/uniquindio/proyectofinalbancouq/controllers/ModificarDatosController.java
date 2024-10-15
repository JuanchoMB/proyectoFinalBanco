package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.ArchivoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.util.List;

public class ModificarDatosController {

    @FXML
    private TextField txtNombreCompleto, txtCorreo, txtDireccion, txtTelefono;
    @FXML
    private PasswordField txtContraseña; // Cambia de TextField a PasswordField

    private Usuario usuarioActual;

    // Establecer el usuario actual desde el controlador principal
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuarioActual != null) {
            cargarDatosUsuario(); // Cargar los datos del usuario
        }
    }

    // Cargar los datos actuales del usuario en los campos de texto
    private void cargarDatosUsuario() {
        txtNombreCompleto.setText(usuarioActual.getNombreCompleto());
        txtCorreo.setText(usuarioActual.getCorreo());
        txtDireccion.setText(usuarioActual.getDireccion());
        txtTelefono.setText(usuarioActual.getTelefono());
        txtContraseña.setText(usuarioActual.getContraseña()); // Cargar la contraseña, pero se verá enmascarada
    }

    @FXML
    public void guardarCambios(ActionEvent event) {
        // Verificar que usuarioActual no sea null
        if (usuarioActual == null) {
            System.out.println("Error: usuarioActual es null.");
            return;
        }

        // Actualizar los datos del usuario con los valores introducidos
        usuarioActual.setNombreCompleto(txtNombreCompleto.getText());
        usuarioActual.setCorreo(txtCorreo.getText());
        usuarioActual.setDireccion(txtDireccion.getText());
        usuarioActual.setTelefono(txtTelefono.getText());
        usuarioActual.setContraseña(txtContraseña.getText()); // Obtener la contraseña del PasswordField

        // Guardar los usuarios actualizados en el archivo
        List<Usuario> usuarios = ArchivoUtil.cargarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(usuarioActual.getId())) {
                usuario.setNombreCompleto(usuarioActual.getNombreCompleto());
                usuario.setCorreo(usuarioActual.getCorreo());
                usuario.setDireccion(usuarioActual.getDireccion());
                usuario.setTelefono(usuarioActual.getTelefono());
                usuario.setContraseña(usuarioActual.getContraseña());
            }
        }
        ArchivoUtil.guardarUsuarios(usuarios);

        // Cerrar la ventana después de guardar los cambios
        Stage stage = (Stage) txtNombreCompleto.getScene().getWindow();
        stage.close();
    }
}

