package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.BazaPodataka;
import main.model.Bolest;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DodavanjeNovogVirusaController {
    @FXML
    private TextField unosNazivVir;
    @FXML
    private TextField unosVrijednostVir;

    public void dodajVirus() throws IOException, SQLException {
        Long id = (long) (BazaPodataka.dohvatiBolesti().size())+4;
        String nazivVir = unosNazivVir.getText();
        Boolean isVirus = true;
        List<Bolest> tmpListaVirusa = new ArrayList<>();
        Bolest noviVir = new Bolest(id, nazivVir, tmpListaVirusa, isVirus);

        BazaPodataka.spremiNovuBolest(noviVir);

        Alert uspjeh = new Alert(Alert.AlertType.INFORMATION);
        uspjeh.setTitle("Informacija o unosu");
        uspjeh.setHeaderText("Uspješan unos!");
        uspjeh.setContentText("Uspješno ste unijeli podatke o virusima!");
        uspjeh.showAndWait();
    }
}
