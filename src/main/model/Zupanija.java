package main.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * klasa koja opisuje zupaniju i nasljeduje klasu ImenovaniEntitet
 */

public class Zupanija extends ImenovaniEntitet implements Serializable {
    private Integer brojStanovnika;
    private Integer brojZarazenih;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zupanija)) return false;
        Zupanija zupanija = (Zupanija) o;
        return Objects.equals(getNaziv(), zupanija.getNaziv()) &&
                Objects.equals(getBrojStanovnika(), zupanija.getBrojStanovnika());
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaziv(), getBrojStanovnika());
    }

    /**
     * konstruktor prima naziv i polje simptoma
     */
    public Zupanija(Long id, String naziv, Integer brojStanovnika, Integer brojZarazenih) {
        super(id, naziv);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenih = brojZarazenih;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }
    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Integer getBrojZarazenih() {
        return brojZarazenih;
    }

    public void setBrojZarazenih(Integer brojZarazenih) {
        this.brojZarazenih = brojZarazenih;
    }
}
