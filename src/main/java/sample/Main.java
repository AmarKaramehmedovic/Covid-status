package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import main.java.sample.Glavna;
import main.model.*;

//import main.model.Datoteke;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pocetniEkran.fxml"));
        primaryStage.setTitle("Covid portal");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        mainStage = primaryStage;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage stage) {
    }

    public static void main(String[] args) throws IOException, SQLException {
        launch(args);
    }
}


