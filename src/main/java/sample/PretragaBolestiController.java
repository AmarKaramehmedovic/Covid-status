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
import main.java.sample.Main;

public class PretragaBolestiController implements Initializable {
    @FXML
    private TextField unosNazivBol;

    @FXML
    private TableView<Bolest> tablicaBolesti;

    @FXML
    private TableColumn<Bolest, String> stupacNazivBol;
    @FXML
    private TableColumn<Bolest, List<Bolest>> stupacSimpBol;


    @FXML
    public void traziBol(ActionEvent event) throws SQLException, IOException {
        String naziv = unosNazivBol.getText().toLowerCase();
        List<Bolest> tmpListaBolesti = new ArrayList<>(BazaPodataka.dohvatiBolesti());
        List<Bolest> filListaBolesti = tmpListaBolesti.stream()
                .filter(b -> b.getNaziv().toLowerCase().contains(naziv))
                .collect(Collectors.toList());
        tmpListaBolesti.clear();
        tmpListaBolesti.addAll(FXCollections.observableArrayList(filListaBolesti));
        tablicaBolesti.setItems(FXCollections.observableList(tmpListaBolesti));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivBol
                .setCellValueFactory(new PropertyValueFactory<>("naziv"));

        stupacSimpBol
                .setCellValueFactory(new PropertyValueFactory<>("simpBol"));

        try {
            tablicaBolesti.setItems(FXCollections.observableList(BazaPodataka.dohvatiBolesti()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
