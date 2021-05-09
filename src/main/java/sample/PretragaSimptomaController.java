package main.java.sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import main.model.Datoteke;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.model.BazaPodataka;
import main.model.Simptom;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaSimptomaController implements Initializable{
    @FXML
    private TextField unosNazivSimp;

    @FXML
    private TableView<Simptom> tablicaSimptoma;

    @FXML
    private TableColumn<Simptom, String> stupacNazivSimp;
    @FXML
    private TableColumn<Simptom, String> stupacVrijednost;


    @FXML
    public void traziSimp(ActionEvent event) throws SQLException, IOException {
        String naziv = unosNazivSimp.getText().toLowerCase();
        List<Simptom> tmpListaSimptoma = new ArrayList<>(BazaPodataka.dohvatiSimptome());
        List<Simptom> filListaSimptoma = tmpListaSimptoma.stream()
                .filter(s -> s.getNaziv().toLowerCase().contains(naziv))
                .collect(Collectors.toList());
        tmpListaSimptoma.clear();
        tmpListaSimptoma.addAll(FXCollections.observableArrayList(filListaSimptoma));
        tablicaSimptoma.setItems(FXCollections.observableList(tmpListaSimptoma));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivSimp
                .setCellValueFactory(new PropertyValueFactory<>("naziv"));
        stupacVrijednost
                .setCellValueFactory(new PropertyValueFactory<>("vrijednost"));

        try {
            tablicaSimptoma.setItems(FXCollections.observableList(BazaPodataka.dohvatiSimptome()));

            tablicaSimptoma.setRowFactory(new Callback<TableView<Simptom>, TableRow<Simptom>>() {
                @Override
                public TableRow<Simptom> call(TableView<Simptom> tableView) {
                    final TableRow<Simptom> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Ukloni");
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            tablicaSimptoma.getItems().remove(row.getItem());

                            try {
                                TablePosition pos = tablicaSimptoma.getSelectionModel().getSelectedCells().get(0);
                                int row = pos.getRow();
                                Simptom nazivSimp = tablicaSimptoma.getItems().get(row);
                                BazaPodataka.ukloniSimptom(stupacNazivSimp.getCellObservableValue(nazivSimp).getValue());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    contextMenu.getItems().add(removeMenuItem);
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu)null)
                                    .otherwise(contextMenu)
                    );
                    return row ;
                }
            });
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
