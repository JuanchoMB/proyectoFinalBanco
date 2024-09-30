package co.edu.uniquindio.proyectofinalbancouq;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML
    private Label etiquetaSaldo;

    @FXML
    private ListView<String> listaTransacciones;

    private Usuario usuarioActual;

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
}
