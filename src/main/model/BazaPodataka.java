package main.model;

import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.SimpleTimeZone;

import static main.java.sample.Main.*;

public class BazaPodataka {
    public static final String DATABASE_CONFIG = "src/main/resources/database.propertis";
    public static Connection konekcijaBaze() throws SQLException, IOException {
        Properties propsDB = new Properties();
        propsDB.load(new FileReader(DATABASE_CONFIG));

        String dbURL = propsDB.getProperty("dbURL");
        String u = propsDB.getProperty("u");
        String pw = propsDB.getProperty("pw");

        Connection veza = DriverManager.getConnection(dbURL, u, pw);
        System.out.println("Uspješna konekcija na bazu!");

        return veza;
    }
    public static void diskonekcijaBaze(Connection veza){
        try {
            veza.close();
            System.out.println("Uspješna diskonekcija sa baze!");

        } catch (SQLException er) {
            er.printStackTrace();
        }
    }

    public static List<Simptom> dohvatiSimptome() throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SIMPTOM");
        System.out.println("Uspješno izvršen query!");

        List<Simptom> listaSimptoma = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            String vr = rs.getString("VRIJEDNOST");

            Simptom noviSimp = new Simptom(id, naziv, vr);
            listaSimptoma.add(noviSimp);
        }
        diskonekcijaBaze(veza);
        return listaSimptoma;
    }
    public static void spremiNoviSimptom(Simptom noviSimp) throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        PreparedStatement upit = veza.prepareStatement("INSERT INTO SIMPTOM(ID, NAZIV, VRIJEDNOST) VALUES(?, ?, ?)");
        upit.setLong(1, noviSimp.getId());
        upit.setString(2, noviSimp.getNaziv());
        upit.setString(3, noviSimp.getVrijednost());
        upit.executeUpdate();
        diskonekcijaBaze(veza);
    }
    public static void ukloniSimptom(String nazivSimp) throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        PreparedStatement upit = veza.prepareStatement("SELECT ID FROM SIMPTOM WHERE NAZIV = ?");
        upit.setString(1, nazivSimp);
        upit.executeQuery();

        ResultSet rs = upit.executeQuery();
        Long id = null;
        while(rs.next()) {
            id = rs.getLong("ID");
        }

        PreparedStatement upit1 = veza
                .prepareStatement("DELETE FROM BOLEST_SIMPTOM WHERE SIMPTOM_ID ="+id+";"+"DELETE FROM SIMPTOM WHERE ID ="+id);
        upit1.executeUpdate();
        diskonekcijaBaze(veza);
    }
    public static List<Bolest> dohvatiBolestiSimptoma() throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        Long idBS = null;
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt
                .executeQuery("SELECT DISTINCT BOLEST.*FROM BOLEST INNER JOIN BOLEST_SIMPTOM ON BOLEST.ID = BOLEST_SIMPTOM.BOLEST_ID WHERE BOLEST_SIMPTOM.SIMPTOM_ID ="+idBS);
        System.out.println("Uspješno izvršen query!");

        List<Bolest> listaSimptomaBol = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            Boolean isVirus = rs.getBoolean("VIRUS");

            Bolest novaBol = new Bolest(id, naziv, listaSimptomaBol, isVirus);
            listaSimptomaBol.add(novaBol);
        }
        diskonekcijaBaze(veza);
        return listaSimptomaBol;
    }

    public static Simptom dohvatiJedanSimptom(Long id) throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        PreparedStatement stmt = veza.prepareStatement("SELECT * FROM SIMPTOM WHERE ID = ?");
        stmt.setLong(1, id);
        stmt.executeQuery();
        ResultSet rs = stmt.executeQuery();
        System.out.println("Uspješno izvršen query!");

        Simptom noviSimp = null;
        while(rs.next()) {
            id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            String vr = rs.getString("VRIJEDNOST");
            noviSimp = new Simptom(id, naziv, vr);
        }
        diskonekcijaBaze(veza);
        return noviSimp;
    }

    public static List<Bolest> dohvatiBolesti() throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOLEST");
        System.out.println("Uspješno izvršen query!");

        List<Bolest> listaBolesti = new ArrayList<>();
        List<Bolest> listaSimpBol = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            Boolean isVirus = rs.getBoolean("VIRUS");

            Bolest novaBol = new Bolest(id, naziv, listaSimpBol, isVirus);
            if(isVirus == false)
                listaBolesti.add(novaBol);
        }
        diskonekcijaBaze(veza);
        return listaBolesti;
    }
    public static void spremiNovuBolest(Bolest novaBol) throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        PreparedStatement upit = veza.prepareStatement("INSERT INTO BOLEST(ID, NAZIV, VIRUS) VALUES(?, ?, ?)");
        upit.setLong(1, novaBol.getId());
        upit.setString(2, novaBol.getNaziv());
        upit.setBoolean(3, novaBol.getIsVirus());
        upit.executeUpdate();
        diskonekcijaBaze(veza);
    }
    public static Bolest dohvatiJednuBolest(Long id) throws SQLException, IOException {
        Connection veza = konekcijaBaze();
        PreparedStatement stmt = veza.prepareStatement("SELECT * FROM BOLEST WHERE ID = ?");
        stmt.setLong(1, id);
        stmt.executeQuery();
        ResultSet rs = stmt.executeQuery();
        System.out.println("Uspješno izvršen query!");

        Bolest novaBol = null;
        while(rs.next()) {
            Statement upit = veza.createStatement();
            ResultSet rs1 = upit.executeQuery("SELECT * FROM BOLEST_SIMPTOM WHERE BOLEST_ID ="+id);
            List<Bolest> listaSimpBol = new ArrayList<>();
            id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            Boolean isVirus = rs.getBoolean("VIRUS");
            novaBol = new Bolest(id, naziv, listaSimpBol, isVirus);
        }
        diskonekcijaBaze(veza);
        return novaBol;
    }
    public static List<Bolest> dohvatiViruse() throws SQLException, IOException  {
        Connection veza = konekcijaBaze();
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOLEST");
        System.out.println("Uspješno izvršen query!");

        List<Bolest> listaVirusa = new ArrayList<>();
        List<Bolest> listaSimpBol = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            Boolean isVirus = rs.getBoolean("VIRUS");

            Bolest noviVir = new Bolest(id, naziv, listaSimpBol, isVirus);
            if(isVirus == true)
                listaVirusa.add(noviVir);
        }
        diskonekcijaBaze(veza);
        return listaVirusa;
    }

    public static List<Zupanija> dohvatiZupanije() throws SQLException, IOException  {
        Connection veza = konekcijaBaze();
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ZUPANIJA");
        System.out.println("Uspješno izvršen query!");

        List<Zupanija> listaZupanija = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String naziv = rs.getString("NAZIV");
            Integer brStan = rs.getInt("BROJ_STANOVNIKA");
            Integer brZar = rs.getInt("BROJ_ZARAZENIH_STANOVNIKA");

            Zupanija novaZup = new Zupanija(id, naziv, brStan, brZar);
            listaZupanija.add(novaZup);
        }
        diskonekcijaBaze(veza);
        return listaZupanija;
    }
    public static void spremiNovuZupaniju(Zupanija novaZup) throws SQLException, IOException  {
        Connection veza = konekcijaBaze();
        PreparedStatement upit = veza
                .prepareStatement("INSERT INTO ZUPANIJA(ID, NAZIV, BROJ_STANOVNIKA, BROJ_ZARAZENIH_STANOVNIKA) VALUES(?, ?, ?, ?)");
        upit.setLong(1, novaZup.getId());
        upit.setString(2, novaZup.getNaziv());
        upit.setInt(3, novaZup.getBrojStanovnika());
        upit.setInt(4, novaZup.getBrojZarazenih());
        upit.executeUpdate();
        diskonekcijaBaze(veza);
    }

    public static List<Osoba> dohvatiOsobe() throws SQLException, IOException  {
        Connection veza = konekcijaBaze();
        Statement stmt = veza.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM OSOBA");
        System.out.println("Uspješno izvršen query!");

        List<Osoba> listaOsoba = new ArrayList<>();
        List<Osoba> listaKontOsoba = new ArrayList<>();

        while(rs.next()){
            Long id = rs.getLong("ID");
            String ime = rs.getString("IME");
            String prezime = rs.getString("PREZIME");
            Date datumRod = rs.getDate("DATUM_RODJENJA");
            Integer zupId = rs.getInt("ZUPANIJA_ID");
            Integer bolId = rs.getInt("BOLEST_ID");

            Integer starost = Period.between(datumRod.toLocalDate(), LocalDate.now()).getYears();

            Osoba novaOsoba = new Osoba(id, ime, prezime, starost, zupId, bolId, listaKontOsoba);
            listaOsoba.add(novaOsoba);
        }
        diskonekcijaBaze(veza);
        return listaOsoba;
    }
    public static void spremiNovuOsobu(Osoba novaOsoba) throws SQLException, IOException  {
        Connection veza = konekcijaBaze();
        PreparedStatement upit = veza
                .prepareStatement("INSERT INTO OSOBA(ID, IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID, BOLEST_ID) VALUES(?, ?, ?, ?, ?, ?)");

        upit.setLong(1, novaOsoba.getId());
        upit.setString(2, novaOsoba.getIme());
        upit.setString(3, novaOsoba.getPrezime());
        upit.setDate(4, Date.valueOf(LocalDate.now()));
        upit.setInt(5, novaOsoba.getZupanija());
        upit.setInt(6, novaOsoba.getZarazenBolescu());
        upit.executeUpdate();
        diskonekcijaBaze(veza);
    }

}
