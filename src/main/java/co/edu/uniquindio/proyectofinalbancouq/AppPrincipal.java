package co.edu.uniquindio.proyectofinalbancouq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppPrincipal extends Application {

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
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
