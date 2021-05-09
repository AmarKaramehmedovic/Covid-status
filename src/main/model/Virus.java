package main.model;

import java.io.Serializable;
import java.util.Set;

/**
 * klasa koja opisuje virus i nasljeduje klasu Bolest
 */

/*public class Virus extends Bolest implements Zarazno, Serializable {
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    *//**
     * konstruktor prima naziv i polje simptoma
     *//*
    public Virus(Long id, String naziv, Boolean isVirus) {
        super(id, naziv, isVirus);
    }

    @Override
    public void prelazakZarazeNaOsobu(Osoba[] osoba) {

    }

    *//**
     * ovverride metoda prelazakZarazeNaOsobu
     *//*
    *//*@Override
    public void prelazakZarazeNaOsobu(Osoba osoba[]) {
        if(osoba != null)
            for (int i = 0; i < osoba.length; i++) {
                Osoba tmp = osoba[i]; tmp.setZarazenBolescu(new Bolest(this.getId(), this.getNaziv(), this.getSimpBolesti()));
        }
    }*//*
}*/
