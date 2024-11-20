package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Categoria;
import co.edu.uniquindio.proyectofinalbancouq.model.Cuenta;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransaccionController {

    private Usuario usuarioActual;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ComboBox<Cuenta> cbCuentaOrigen;

    @FXML
    private ComboBox<Cuenta> cbCuentaDestino;

    @FXML
    private ComboBox<Categoria> cbCategoria;

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        cbCuentaOrigen.getItems().addAll(usuario.getCuentasAsociadas());
        cbCuentaDestino.getItems().addAll(usuario.getCuentasAsociadas());
    }

    @FXML
    private void manejarDeposito() {
        double cantidad = Double.parseDouble(txtCantidad.getText());
        String descripcion = txtDescripcion.getText();
        Cuenta cuentaDestino = cbCuentaDestino.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (cuentaDestino != null) {
            usuarioActual.depositar(cantidad, descripcion, cuentaDestino, categoria);
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Error: Debes seleccionar una cuenta de destino.");
        }
    }

    @FXML
    private void manejarRetiro() {
        double cantidad = Double.parseDouble(txtCantidad.getText());
        String descripcion = txtDescripcion.getText();
        Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (cuentaOrigen != null && usuarioActual.retirar(cantidad, descripcion, cuentaOrigen, categoria)) {
            System.out.println("Retiro realizado.");
        } else {
            System.out.println("Error: Saldo insuficiente o cuenta no seleccionada.");
        }
    }

    @FXML
    private void manejarTransferencia() {
        double cantidad = Double.parseDouble(txtCantidad.getText());
        String descripcion = txtDescripcion.getText();
        Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
        Cuenta cuentaDestino = cbCuentaDestino.getValue();
        Categoria categoria = cbCategoria.getValue();

        if (cuentaOrigen != null && cuentaDestino != null && usuarioActual.retirar(cantidad, descripcion, cuentaOrigen, categoria)) {
            usuarioActual.depositar(cantidad, descripcion, cuentaDestino, categoria);
            System.out.println("Transferencia realizada.");
        } else {
            System.out.println("Error en la transferencia.");
        }
    }
}
