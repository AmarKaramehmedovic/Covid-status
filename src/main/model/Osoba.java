package main.model;
//import hr.java.covidportal.main.model.Virus

import java.util.List;
import java.util.Objects;

/**
 * klasa koja opisuje osobu
 */

public class Osoba{
    private Long id;
    private String ime;
    private String prezime;
    private Integer starost;
    private Integer zupanija;
    private Integer zarazenBolescu;
    private List<Osoba> listaKontaktiraneOsobe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Osoba)) return false;
        Osoba osoba = (Osoba) o;
        return Objects.equals(getIme(), osoba.getIme()) &&
                Objects.equals(getPrezime(), osoba.getPrezime()) &&
                Objects.equals(getStarost(), osoba.getStarost()) &&
                Objects.equals(getZupanija(), osoba.getZupanija()) &&
                Objects.equals(getZarazenBolescu(), osoba.getZarazenBolescu()) &&
                Objects.equals(getListaKontaktiraneOsobe(), osoba.getListaKontaktiraneOsobe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIme(), getPrezime(), getStarost(), getZupanija(), getZarazenBolescu(), getListaKontaktiraneOsobe());
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Integer getZupanija() {
        return zupanija;
    }

    public void setZupanija(Integer zupanija) {
        this.zupanija = zupanija;
    }

    public Integer getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Integer zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public List<Osoba> getListaKontaktiraneOsobe() {
        return listaKontaktiraneOsobe;
    }

    public void setListaKontaktiraneOsobe(List<Osoba> listaKontaktiraneOsobe) {
        this.listaKontaktiraneOsobe = listaKontaktiraneOsobe;
    }

    public Osoba(Long id, String ime, String prezime, Integer starost, Integer zupanija, Integer zarazenBolescu, List<Osoba> listaKontaktiraneOsobe){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.starost = starost;
        this.zupanija = zupanija;
        this.zarazenBolescu = zarazenBolescu;
        this.listaKontaktiraneOsobe = listaKontaktiraneOsobe;

        /*if(zarazenBolescu instanceof Virus virus)
            virus.prelazakZarazeNaOsobu(listaKontaktiraneOsobe);*/

    }



}
