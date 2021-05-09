package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetniEkranController implements Initializable {
    @FXML
    public void prikaziEkranZaPretraguZupanija(ActionEvent event) throws IOException {
        Parent pretragaZupanijaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaZupanijaScene);
    }
    @FXML
    public void prikaziEkranZaDodavanjeNovihZupanija(ActionEvent event) throws IOException {
        Parent dodavanjeZupanijaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNoveZupanije.fxml"));
        Scene dodavanjeZupanijaScene = new Scene(dodavanjeZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeZupanijaScene);
    }


    @FXML
    public void prikaziEkranZaPretraguSimptoma(ActionEvent event) throws IOException {
        Parent pretragaSimptomaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaSimptomaScene);
    }
    @FXML
    public void prikaziEkranZaDodavanjeNovihSimptoma(ActionEvent event) throws IOException {
        Parent dodavanjeSimptomaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNovogSimptoma.fxml"));
        Scene dodavanjeSimptomaScene = new Scene(dodavanjeSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeSimptomaScene);
    }


    @FXML
    public void prikaziEkranZaPretraguBolesti(ActionEvent event) throws IOException {
        Parent pretragaBolestiFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);
        Main.getMainStage().setScene(pretragaBolestiScene);
    }
    @FXML
    public void prikaziEkranZaDodavanjeNovihBolesti(ActionEvent event) throws IOException {
        Parent dodavanjeBolestiFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNoveBolesti.fxml"));
        Scene dodavanjeBolestiScene = new Scene(dodavanjeBolestiFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeBolestiScene);
    }


    @FXML
    public void prikaziEkranZaPretraguVirusa(ActionEvent event) throws IOException {
        Parent pretragaVirusaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaVirusaScene);
    }
    @FXML
    public void prikaziEkranZaDodavanjeNovihVirusa(ActionEvent event) throws IOException {
        Parent dodavanjeVirusaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNovogVirusa.fxml"));
        Scene dodavanjeVirusaScene = new Scene(dodavanjeVirusaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeVirusaScene);
    }



    @FXML
    public void prikaziEkranZaPretraguOsoba(ActionEvent event) throws IOException {
        Parent pretragaOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaOsobaScene);
    }
    @FXML
    public void prikaziEkranZaDodavanjeNovihOsoba(ActionEvent event) throws IOException {
        Parent dodavanjeOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNoveOsobe.fxml"));
        Scene dodavanjeOsobaScene = new Scene(dodavanjeOsobaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeOsobaScene);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
