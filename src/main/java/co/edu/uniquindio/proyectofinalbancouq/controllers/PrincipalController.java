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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;

public class PrincipalController {

    @FXML
    private Label etiquetaSaldo;

    @FXML
    private ListView<String> listaTransacciones;

    private Usuario usuarioActual;
    public Label etiquetaMensaje;

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        actualizarUI();
    }

    public void actualizarUI() {
        etiquetaSaldo.setText(String.valueOf(usuarioActual.getSaldo()));
        listaTransacciones.getItems().clear();
        for (Transaccion transaccion : usuarioActual.getTransacciones()) {
            listaTransacciones.getItems().add(transaccion.toString());
        }
    }

    @FXML
    private void manejarDeposito() {
        double cantidadDeposito = 50; // Ejemplo
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
        System.out.println("Botón de Retiro presionado");
        try {
            // Cargar la interfaz de retiro desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RetiroView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de retiro
            RetiroController retiroController = loader.getController();

            // Pasar el usuario actual al controlador de retiro
            retiroController.setUsuarioActual(usuarioActual);
            // Pasar la referencia del PrincipalController
            retiroController.setPrincipalController(this); // Asegúrate de que esto se llame

            // Crear una nueva ventana para el retiro
            Stage stage = new Stage();
            stage.setTitle("Retiro de Dinero");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
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
            modificarController.setUsuarioActual(usuarioActual); // Pasar el usuario actual

            // Crear una nueva ventana para la modificación de datos
            Stage stage = new Stage();
            stage.setTitle("Modificar Datos Personales");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


