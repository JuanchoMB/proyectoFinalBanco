package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Categoria;
import co.edu.uniquindio.proyectofinalbancouq.model.Cuenta;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.awt.*;

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

    // Lógica para cuando se selecciona "Depositar"
    @FXML
    private void manejarDeposito() {
        try {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            String descripcion = txtDescripcion.getText();
            Cuenta cuentaDestino = cbCuentaDestino.getValue();
            Categoria categoria = cbCategoria.getValue();

            if (cuentaDestino != null) {
                usuarioActual.depositar(cantidad, descripcion, cuentaDestino, categoria);
                System.out.println("Depósito realizado correctamente.");
            } else {
                System.out.println("Error: Debes seleccionar una cuenta de destino.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Cantidad inválida.");
        }
    }

    // Lógica para cuando se selecciona "Retirar"
    @FXML
    private void manejarRetiro() {
        try {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            String descripcion = txtDescripcion.getText();
            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            Categoria categoria = cbCategoria.getValue();

            if (cuentaOrigen != null && usuarioActual.retirar(cantidad, descripcion, cuentaOrigen, categoria)) {
                System.out.println("Retiro realizado correctamente.");
            } else {
                System.out.println("Error: Saldo insuficiente o no seleccionaste una cuenta de origen.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Cantidad inválida.");
        }
    }

    // Lógica para cuando se selecciona "Transferir"
    @FXML
    private void manejarTransferencia() {
        try {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            String descripcion = txtDescripcion.getText();
            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            Cuenta cuentaDestino = cbCuentaDestino.getValue();
            Categoria categoria = cbCategoria.getValue();

            if (cuentaOrigen != null && cuentaDestino != null) {
                usuarioActual.retirar(cantidad, descripcion, cuentaOrigen, categoria); // Retirar de la cuenta origen
                usuarioActual.depositar(cantidad, descripcion, cuentaDestino, categoria); // Depositar en la cuenta destino
                System.out.println("Transferencia realizada correctamente.");
            } else {
                System.out.println("Error: Debes seleccionar tanto la cuenta de origen como la de destino.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Cantidad inválida.");
        }
    }


}

