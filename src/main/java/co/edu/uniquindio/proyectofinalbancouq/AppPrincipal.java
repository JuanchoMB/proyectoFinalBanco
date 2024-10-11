package co.edu.uniquindio.proyectofinalbancouq;

import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;
import co.edu.uniquindio.proyectofinalbancouq.util.SerializacionUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AppPrincipal extends Application {

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        // Cargar usuarios desde archivo binario o XML al iniciar
        List<Usuario> usuarios = SerializacionUtil.cargarUsuariosBinario();
        if (usuarios == null) {
            usuarios = SerializacionUtil.cargarUsuariosXML();
        }
        // Establecer la lista de usuarios en el controlador o en la aplicación

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        VBox raiz = loader.load();
        Scene escena = new Scene(raiz);

        escenarioPrincipal.setTitle("Billetera Virtual");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

