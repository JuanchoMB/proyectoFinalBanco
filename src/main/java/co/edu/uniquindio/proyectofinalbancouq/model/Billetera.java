package co.edu.uniquindio.proyectofinalbancouq.model;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Objects;

public class Billetera implements Serializable {










    public void loadStage(String url, Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(Billetera.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
