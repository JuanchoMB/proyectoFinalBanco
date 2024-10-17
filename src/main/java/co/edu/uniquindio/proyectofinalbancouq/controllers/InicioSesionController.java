package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Billetera;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.LogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InicioSesionController {

    @FXML
    private TextField campoID;

    @FXML
    private PasswordField campoContraseña;

    @FXML
    private Label etiquetaMensaje;

    private Usuario usuarioActual;




    @FXML
    void manejarIniciarSesion() {
        String id = campoID.getText();
        String contraseña = campoContraseña.getText();

        usuarioActual = verificarCredenciales(id, contraseña);
        if(id.equals("admin@billetera.com")&& contraseña.equals("admin123")){

        }
        if (usuarioActual != null) {
            etiquetaMensaje.setText("Inicio de sesión exitoso");
            // Registrar en el archivo Log el inicio de sesión
            LogUtil.registrarAccion(usuarioActual.getId(), "Inicio de sesión exitoso");
            cargarPaginaPrincipal();
        } else {
            etiquetaMensaje.setText("ID o contraseña incorrectos");
            // Registrar intento fallido
            LogUtil.registrarAccion("Desconocido", "Intento de inicio de sesión fallido");
        }
    }

    // Método para verificar las credenciales del usuario
    private Usuario verificarCredenciales(String id, String contraseña) {
        for (Usuario usuario : RegistroController.getUsuariosRegistrados()) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña)) {
                return usuario;
            }
        }
        return null;
    }

    private void cargarPaginaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipalView.fxml"));
            VBox pantallaPrincipal = loader.load();

            // Obtener el controlador del PrincipalController
            PrincipalController principalController = loader.getController();
            principalController.setUsuarioActual(usuarioActual); // Pasar el usuario actual al PrincipalController

            // Cambiar la escena para mostrar la vista principal
            Scene escenaPrincipal = new Scene(pantallaPrincipal);
            Stage stage = (Stage) campoID.getScene().getWindow();
            stage.setScene(escenaPrincipal);
            stage.show();
        } catch (Exception e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    @FXML
    private void irARegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistroView.fxml"));
            VBox pantallaRegistro = loader.load();
            Scene escenaRegistro = new Scene(pantallaRegistro);
            Stage stage = (Stage) campoID.getScene().getWindow();
            stage.setScene(escenaRegistro);
        } catch (Exception e) {
            LogUtil.registrarExcepcion(e);
        }
    }
}
