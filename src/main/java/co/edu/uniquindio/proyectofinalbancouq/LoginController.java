package co.edu.uniquindio.proyectofinalbancouq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField campoID;

    @FXML
    private PasswordField campoContraseña;

    @FXML
    private Label etiquetaMensaje;

    // No es necesario un gestor de usuarios separado si usamos RegistroController
    // private GestorUsuarios gestorUsuarios = new GestorUsuarios();

    private Usuario usuarioActual;

    @FXML
    private void manejarIniciarSesion() {
        String id = campoID.getText();
        String contraseña = campoContraseña.getText();

        // Verificar credenciales en la lista de usuarios registrados
        usuarioActual = verificarCredenciales(id, contraseña);

        if (usuarioActual != null) {
            etiquetaMensaje.setText("Inicio de sesión exitoso");
            // Cambiar a la pantalla principal
            cargarPaginaPrincipal();
        } else {
            etiquetaMensaje.setText("ID o contraseña incorrectos");
        }
    }

    // Método para verificar las credenciales del usuario
    private Usuario verificarCredenciales(String id, String contraseña) {
        for (Usuario usuario : RegistroController.getUsuariosRegistrados()) {
            if (usuario.getId().equals(id) && usuario.getContraseña().equals(contraseña)) {
                return usuario; // Retornar el usuario si las credenciales son correctas
            }
        }
        return null; // Retornar null si no se encuentra el usuario
    }

    private void cargarPaginaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalView.fxml")); // Ajusta el nombre del archivo según tu proyecto
            VBox pantallaPrincipal = loader.load();
            Scene escenaPrincipal = new Scene(pantallaPrincipal);
            Stage stage = (Stage) campoID.getScene().getWindow();
            stage.setScene(escenaPrincipal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irARegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistroView.fxml"));
            VBox pantallaRegistro = loader.load();
            Scene escenaRegistro = new Scene(pantallaRegistro);
            Stage stage = (Stage) campoID.getScene().getWindow();
            stage.setScene(escenaRegistro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
