package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.model.CuentaCorriente;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class CuentaCorrienteController {

    @FXML
    private TextField numeroCuenta;

    @FXML
    private TextField saldoCuenta;

    @FXML
    private TextField sobregiro;

    private CuentaCorriente cuentaCorriente;

    public void inicializar(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
        numeroCuenta.setText(cuentaCorriente.getNumeroCuenta());
        saldoCuenta.setText(String.valueOf(cuentaCorriente.consultarSaldo()));
        sobregiro.setText(String.valueOf(cuentaCorriente.getSobregiro()));
    }

    @FXML
    public void realizarDeposito(double monto) {
        cuentaCorriente.depositar(monto);
        saldoCuenta.setText(String.valueOf(cuentaCorriente.consultarSaldo()));
    }

    @FXML
    public void realizarRetiro(double monto) {
        cuentaCorriente.retirar(monto);
        saldoCuenta.setText(String.valueOf(cuentaCorriente.consultarSaldo()));
    }
}