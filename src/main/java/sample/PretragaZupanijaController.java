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
import main.model.Bolest;
import main.model.Zupanija;
//import main.java.sample.Glavna;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaZupanijaController implements Initializable {
    @FXML
    private TextField unosNazivZup;

    @FXML
    private TableView<Zupanija> tablicaZupanija;

    @FXML
    private TableColumn<Zupanija, String> stupacNazivZup;
    @FXML
    private TableColumn<Zupanija, Integer> stupacBrStan;
    @FXML
    private TableColumn<Zupanija, Integer> stupacBrZarazenih;


    @FXML
    public void traziZup(ActionEvent event) throws SQLException, IOException {
        String naziv = unosNazivZup.getText().toLowerCase();
        List<Zupanija> tmpListaZupanija = new ArrayList<>(BazaPodataka.dohvatiZupanije());
        List<Zupanija> filListaZupanija = tmpListaZupanija.stream()
                .filter(zupanija -> zupanija.getNaziv().toLowerCase().contains(naziv))
                .collect(Collectors.toList());
        tmpListaZupanija.clear();
        tmpListaZupanija.addAll(FXCollections.observableArrayList(filListaZupanija));
        tablicaZupanija.setItems(FXCollections.observableList(tmpListaZupanija));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivZup
                .setCellValueFactory(new PropertyValueFactory<>("naziv"));
        stupacBrStan
                .setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));
        stupacBrZarazenih
                .setCellValueFactory(new PropertyValueFactory<>("brojZarazenih"));

        //List<Zupanija> listaZupanija = new ArrayList<>(Main.listaZupanija);
        try {
            tablicaZupanija.setItems(FXCollections.observableList(BazaPodataka.dohvatiZupanije()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
