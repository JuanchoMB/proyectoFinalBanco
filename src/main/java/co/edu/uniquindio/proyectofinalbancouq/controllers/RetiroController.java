package co.edu.uniquindio.proyectofinalbancouq.controllers;

import co.edu.uniquindio.proyectofinalbancouq.controllers.PrincipalController;
import co.edu.uniquindio.proyectofinalbancouq.model.TipoTransaccion;
import co.edu.uniquindio.proyectofinalbancouq.model.Transaccion;
import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.LogUtil;
import co.edu.uniquindio.proyectofinalbancouq.util.TransaccionUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RetiroController {

    @FXML
    private TextField campoCantidadRetiro; // Campo donde el usuario introduce la cantidad
    @FXML
    private Label etiquetaMensaje; // Etiqueta para mostrar mensajes al usuario
    @FXML
    private Button confirmarRetiroButton;

    private Usuario usuarioActual; // El usuario actual que va a realizar el retiro

    private PrincipalController principalController; // Añadir referencia al PrincipalController

    // Metodo para inicializar el controlador con el usuario actual
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    // Metodo para establecer la referencia al PrincipalController
    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController; // Guardar la referencia
    }

    // Metodo llamado cuando el usuario hace clic en el botón "Confirmar Retiro"
    @FXML
    private void confirmarRetiro() {
        String cantidadTexto = campoCantidadRetiro.getText(); // Obtener el texto del campo
        try {
            double cantidadRetiro = Double.parseDouble(cantidadTexto); // Convertir a double

            // Verificar si el saldo del usuario es suficiente
            if (usuarioActual.getSaldo() >= cantidadRetiro) {
                // Realizar el retiro
                usuarioActual.retirar(cantidadRetiro);

                // Crear y guardar la transacción
                Transaccion transaccion = new Transaccion("RET-" + System.currentTimeMillis(), cantidadRetiro, TipoTransaccion.RETIRO, null);
                TransaccionUtil.guardarTransaccion(transaccion);

                // Registrar en el log
                LogUtil.registrarAccion(usuarioActual.getId(), "Retiro realizado: " + cantidadRetiro);

                // Actualizar la UI en el PrincipalController
                if (principalController != null) {
                    principalController.actualizarUI(); // Llamar al metodo para actualizar el saldo
                }

                etiquetaMensaje.setText("Retiro realizado con éxito.");
                etiquetaMensaje.setStyle("-fx-text-fill: green;");

                // Cerrar la ventana después de realizar el retiro
                cerrarVentana();
            } else {
                etiquetaMensaje.setText("Saldo insuficiente.");
                etiquetaMensaje.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            // Mostrar un mensaje si el valor ingresado no es un número válido
            etiquetaMensaje.setText("Por favor, ingrese una cantidad válida.");
            etiquetaMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    // Metodo para cerrar la ventana de retiro cuando se hace clic en "Cancelar"
    @FXML
    private void cancelarRetiro() {
        cerrarVentana();
    }

    // Metodo para cerrar la ventana
    private void cerrarVentana() {
        Stage stage = (Stage) confirmarRetiroButton.getScene().getWindow();
        stage.close();
    }
}


