module co.edu.uniquindio.proyectofinalbancouq {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;

    opens co.edu.uniquindio.proyectofinalbancouq to javafx.fxml;
    exports co.edu.uniquindio.proyectofinalbancouq;
    exports co.edu.uniquindio.proyectofinalbancouq.model;
    opens co.edu.uniquindio.proyectofinalbancouq.model to javafx.fxml;
    exports co.edu.uniquindio.proyectofinalbancouq.controllers;
    opens co.edu.uniquindio.proyectofinalbancouq.controllers to javafx.fxml;

}