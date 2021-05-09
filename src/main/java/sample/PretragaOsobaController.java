package main.java.sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import main.model.Datoteke;
import main.model.BazaPodataka;
import main.model.Osoba;
import main.model.Simptom;
import main.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaOsobaController implements Initializable {
    @FXML
    private TextField unosImeOsobe;

    @FXML
    private TableView<Osoba> tablicaOsoba;

    @FXML
    private TableColumn<Osoba, String> stupacIme;
    @FXML
    private TableColumn<Osoba, String> stupacPrezime;
    @FXML
    private TableColumn<Osoba, String> stupacStarost;
    @FXML
    private TableColumn<Osoba, String> stupacZupanija;
    @FXML
    private TableColumn<Osoba, String> stupacZarazenBol;
    @FXML
    private TableColumn<Osoba, String> stupacKontOsobe;


    @FXML
    public void traziOsobu(ActionEvent event) throws SQLException, IOException {
        String ime = unosImeOsobe.getText().toLowerCase();
        List<Osoba> listaOsoba = new ArrayList<>(BazaPodataka.dohvatiOsobe());
        List<Osoba> filListaOsoba = listaOsoba.stream()
                .filter(o -> o.getIme().toLowerCase().contains(ime))
                .collect(Collectors.toList());
        listaOsoba.clear();
        listaOsoba.addAll(FXCollections.observableArrayList(filListaOsoba));
        tablicaOsoba.setItems(FXCollections.observableList(listaOsoba));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacIme
                .setCellValueFactory(new PropertyValueFactory<>("ime"));
        stupacPrezime
                .setCellValueFactory(new PropertyValueFactory<>("prezime"));
        stupacStarost
                .setCellValueFactory(new PropertyValueFactory<>("starost"));
        stupacZupanija
                .setCellValueFactory(new PropertyValueFactory<>("zupanija"));
        stupacZarazenBol
                .setCellValueFactory(new PropertyValueFactory<>("zarazenBolescu"));
        stupacKontOsobe
                .setCellValueFactory(new PropertyValueFactory<>("listaKontaktiraneOsobe"));

        try {
            tablicaOsoba.setItems(FXCollections.observableList(BazaPodataka.dohvatiOsobe()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
