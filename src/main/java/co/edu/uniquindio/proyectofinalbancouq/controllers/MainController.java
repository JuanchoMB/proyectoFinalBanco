package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.TipoTransaccion;
import co.edu.uniquindio.proyectofinalbancouq.model.Transaccion;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.TransaccionUtil;
import co.edu.uniquindio.proyectofinalbancouq.util.LogUtil;
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
        double cantidadDeposito = 100.0; // Ejemplo
        usuarioActual.depositar(cantidadDeposito);
        Transaccion transaccion = new Transaccion("DEP-" + System.currentTimeMillis(), cantidadDeposito, TipoTransaccion.DEPOSITO, null);

        // Guardar la transacción
        TransaccionUtil.guardarTransaccion(transaccion);

        // Registrar en el archivo Log
        LogUtil.registrarAccion(usuarioActual.getId(), "Depósito realizado: " + cantidadDeposito);

        actualizarUI();
    }

    @FXML
    private void manejarRetiro() {
        double cantidadRetiro = 50.0; // Ejemplo
        if (usuarioActual.getSaldo() >= cantidadRetiro) {
            usuarioActual.retirar(cantidadRetiro);
            Transaccion transaccion = new Transaccion("RET-" + System.currentTimeMillis(), cantidadRetiro, TipoTransaccion.RETIRO, null);

            // Guardar la transacción
            TransaccionUtil.guardarTransaccion(transaccion);

            // Registrar en el archivo Log
            LogUtil.registrarAccion(usuarioActual.getId(), "Retiro realizado: " + cantidadRetiro);

            actualizarUI();
        } else {
            etiquetaMensaje.setText("Saldo insuficiente.");
        }
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
    @FXML
    public void modificarDatosPersonales(ActionEvent event) {
        try {
            // Cargar la vista de modificación de datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditarUsuarioView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista
            ModificarDatosController modificarController = loader.getController();
            modificarController.setUsuarioActual(usuarioActual);

            // Crear una nueva ventana para la modificación de datos
            Stage stage = new Stage();
            stage.setTitle("Modificar Datos Personales");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}

