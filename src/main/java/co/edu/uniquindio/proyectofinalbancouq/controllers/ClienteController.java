package co.edu.uniquindio.proyectofinalbancouq.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import co.edu.uniquindio.proyectofinalbancouq.model.Cliente;
import co.edu.uniquindio.proyectofinalbancouq.model.Cuenta;

public class ClienteController {

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField identificacionCliente;

    @FXML
    private ListView<Cuenta> listaCuentas;

    private Cliente cliente;

    public void inicializar(Cliente cliente) {
        this.cliente = cliente;
        nombreCliente.setText(cliente.getNombre());
        identificacionCliente.setText(cliente.getIdentificacion());
        listaCuentas.getItems().addAll(cliente.getCuentas());
    }

    @FXML
    public void agregarCuenta(Cuenta cuenta) {
        cliente.abrirCuenta(cuenta);
        listaCuentas.getItems().add(cuenta);
    }
}