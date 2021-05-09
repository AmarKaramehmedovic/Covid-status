package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.BazaPodataka;
import main.model.Bolest;
import main.model.Osoba;
import main.model.Zupanija;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DodavanjeNoveOsobeController {
    @FXML
    private TextField unosIme;
    @FXML
    private TextField unosPrezime;
    @FXML
    private TextField unosStarost;
    @FXML
    private TextField unosZup;
    @FXML
    private TextField unosIdBolOs;
    @FXML
    private TextField unosIdKontOs;

    public void dodajOsobu() throws IOException, SQLException, ParseException {
        Long id = (long) (BazaPodataka.dohvatiOsobe().size())+1;
        String ime = unosIme.getText();
        String prezime = unosPrezime.getText();
        Integer starost = Integer.parseInt(unosStarost.getText());
        Integer idZup = Integer.parseInt(unosZup.getText());
        Integer idBolOs = Integer.parseInt(unosIdBolOs.getText());
        List<Osoba> listaKontOs = new ArrayList<>();

        Osoba novaOsoba = new Osoba(id, ime, prezime, starost, idZup, idBolOs, listaKontOs);
        BazaPodataka.spremiNovuOsobu(novaOsoba);

        Alert uspjeh = new Alert(Alert.AlertType.INFORMATION);
        uspjeh.setTitle("Informacija o unosu");
        uspjeh.setHeaderText("Uspješan unos!");
        uspjeh.setContentText("Uspješno ste unijeli podatke o virusima!");
        uspjeh.showAndWait();
    }
}
