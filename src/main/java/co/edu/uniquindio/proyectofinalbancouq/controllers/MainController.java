package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Transaccion;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Label etiquetaSaldo;

    @FXML
    private ListView<String> listaTransacciones;

    private Usuario usuarioActual;
    public Label etiquetaMensaje;

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        actualizarUI();
    }

    private void actualizarUI() {
        etiquetaSaldo.setText(String.valueOf(usuarioActual.getSaldo()));
        listaTransacciones.getItems().clear();
        for (Transaccion transaccion : usuarioActual.getTransacciones()) {
            listaTransacciones.getItems().add(transaccion.toString());
        }
    }

    @FXML
    private void manejarDeposito() {
        // Lógica para agregar un depósito
        usuarioActual.depositar(100.0); // Ejemplo de depósito
        actualizarUI();
    }

    @FXML
    private void manejarRetiro() {
        // Lógica para retirar saldo
        usuarioActual.retirar(50.0); // Ejemplo de retiro
        actualizarUI();
    }
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
}
