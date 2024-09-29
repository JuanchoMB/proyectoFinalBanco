package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.Banco;
import co.edu.uniquindio.proyectofinalbancouq.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;

public class BancoController {

    @FXML
    private TextField nombreBanco;

    @FXML
    private ListView<Cliente> listaClientes;

    private Banco banco;

    public void inicializar(Banco banco) {
        this.banco = banco;
        nombreBanco.setText(banco.getNombreBanco());
        listaClientes.getItems().addAll(banco.getClientes());
    }

    // Lógica para agregar clientes, etc.

    @FXML
    public void guardarBanco() {
        try {
            banco.guardarDatos("banco.dat");
            System.out.println("Datos del banco guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cargarBanco() {
        try {
            banco = Banco.cargarDatos("banco.dat");
            nombreBanco.setText(banco.getNombreBanco());
            listaClientes.getItems().clear();
            listaClientes.getItems().addAll(banco.getClientes());
            System.out.println("Datos del banco cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}