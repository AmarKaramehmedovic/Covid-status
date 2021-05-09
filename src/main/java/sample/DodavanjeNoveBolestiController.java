package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.BazaPodataka;
import main.model.Bolest;
import main.model.Osoba;
import main.model.Simptom;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DodavanjeNoveBolestiController {
    @FXML
    private TextField unosNazivBol;
    @FXML
    private TextField unosVrijednost;

    public void dodajBol() throws IOException, SQLException {
        Long id = (long) (BazaPodataka.dohvatiBolesti().size())+4;
        String nazivBol = unosNazivBol.getText();
        Boolean isVirus = false;
        List<Bolest> tmpListaBolesti = new ArrayList<>();
        Bolest novaBol = new Bolest(id, nazivBol, tmpListaBolesti, isVirus);

        BazaPodataka.spremiNovuBolest(novaBol);

        Alert uspjeh = new Alert(Alert.AlertType.INFORMATION);
        uspjeh.setTitle("Informacija o unosu");
        uspjeh.setHeaderText("Uspješan unos!");
        uspjeh.setContentText("Uspješno ste unijeli podatke o bolestima!");
        uspjeh.showAndWait();
    }
}
