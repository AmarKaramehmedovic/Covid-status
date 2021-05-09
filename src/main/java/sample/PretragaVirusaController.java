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
import main.model.BazaPodataka;
import main.model.Bolest;
//import main.model.Datoteke;
import main.model.Simptom;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class PretragaVirusaController implements Initializable {
    @FXML
    private TextField unosNazivVir;

    @FXML
    private TableView<Bolest> tablicaVirusa;

    @FXML
    private TableColumn<Bolest, String> stupacNazivVir;
    @FXML
    private TableColumn<Bolest, List<Bolest>> stupacSimpVir;

    @FXML
    public void traziVir(ActionEvent event) throws SQLException, IOException {
        String naziv = unosNazivVir.getText().toLowerCase();
        List<Bolest> listaVirusa = new ArrayList<>(BazaPodataka.dohvatiViruse());
        List<Bolest> filListaVirusa = listaVirusa.stream()
                .filter(v -> v.getNaziv().toLowerCase().contains(naziv))
                .collect(Collectors.toList());
        listaVirusa.clear();
        listaVirusa.addAll(FXCollections.observableArrayList(filListaVirusa));
        tablicaVirusa.setItems(FXCollections.observableList(listaVirusa));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivVir
                .setCellValueFactory(new PropertyValueFactory<>("naziv"));

        stupacSimpVir
                .setCellValueFactory(new PropertyValueFactory<>("simpBol"));

        try {
            tablicaVirusa.setItems(FXCollections.observableList(BazaPodataka.dohvatiViruse()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
