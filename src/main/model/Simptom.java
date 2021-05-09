package main.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * klasa koja opisuje simptom i nasljeduje klasu ImenovaniEntitet
 */

public class Simptom extends ImenovaniEntitet implements Serializable {
    private String vrijednost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simptom)) return false;
        Simptom simptom = (Simptom) o;
        return Objects.equals(getNaziv(), simptom.getNaziv()) &&
                Objects.equals(getVrijednost(), simptom.getVrijednost());
    }

    @Override
    public String toString() {
        return id + " " + naziv  + " " + vrijednost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaziv(), getVrijednost());
    }

    /**
     * konstruktor prima naziv i polje simptoma
     */
    public Simptom(Long id, String naziv, String vrijednost) {
        super(id, naziv);
        //this.naziv = naziv;
        this.vrijednost = vrijednost;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }
}
