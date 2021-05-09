package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.BazaPodataka;
import main.model.Simptom;

import java.io.*;
import java.sql.SQLException;

public class DodavanjeNovogSimptomaController {
    @FXML
    private TextField unosNazivSimp;
    @FXML
    private TextField unosVr;

    public void dodajSimp() throws IOException, SQLException {
        Long id = (long) (BazaPodataka.dohvatiSimptome().size())+1;
        String nazivSimp = unosNazivSimp.getText();
        String vr = unosVr.getText();
        Simptom noviSimp = new Simptom(id, nazivSimp, vr);

        BazaPodataka.spremiNoviSimptom(noviSimp);

        Alert uspjeh = new Alert(Alert.AlertType.INFORMATION);
        uspjeh.setTitle("Informacija o unosu");
        uspjeh.setHeaderText("Uspješan unos!");
        uspjeh.setContentText("Uspješno ste unijeli podatke o simptomima!");
        uspjeh.showAndWait();
    }
}
