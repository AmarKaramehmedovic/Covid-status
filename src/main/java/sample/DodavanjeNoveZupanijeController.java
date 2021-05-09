package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.BazaPodataka;
import main.model.Bolest;
import main.model.Zupanija;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DodavanjeNoveZupanijeController{
    @FXML
    private TextField unosNazivZup;
    @FXML
    private TextField unosBrStan;
    @FXML
    private TextField unosBrZarazenih;

    public void dodajZup() throws IOException, SQLException {
        Long id = (long) (BazaPodataka.dohvatiZupanije().size())+1;
        String nazivZup = unosNazivZup.getText();
        Integer brStan = Integer.parseInt(unosBrStan.getText());
        Integer brZarazenih = Integer.parseInt(unosBrZarazenih.getText());

        Zupanija novaZup = new Zupanija(id, nazivZup, brStan, brZarazenih);
        BazaPodataka.spremiNovuZupaniju(novaZup);


        Alert uspjeh = new Alert(Alert.AlertType.INFORMATION);
        uspjeh.setTitle("Informacija o unosu");
        uspjeh.setHeaderText("Uspješan unos!");
        uspjeh.setContentText("Uspješno ste unijeli podatke o simptomima!");
        uspjeh.showAndWait();
    }
}
