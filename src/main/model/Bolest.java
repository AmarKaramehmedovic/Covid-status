package main.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * klasa koja opisuje bolest i nasljeduje klasu ImenovaniEntitet
 */

public class Bolest extends ImenovaniEntitet implements Serializable {
    private List<Bolest> simpBol;
    private Boolean isVirus;

    @Override
    public String toString() {
        return naziv;
    }

    /**
     * konstruktor prima naziv i boolean isVirus
     */
    public Bolest(Long id, String naziv, List<Bolest>simpBol, Boolean isVirus) {
        super(id, naziv);
        this.simpBol = simpBol;
        this.isVirus = isVirus;
    }

    /**
     * getters and setters klase Bolest
     */
    public Boolean getIsVirus() {
        return isVirus;
    }

    public void setIsVirus(Boolean isVirus) {
        this.isVirus = isVirus;
    }

    public List<Bolest> getSimpBol() {
        return simpBol;
    }

    public void setSimpBol(List<Bolest> simpBol) {
        this.simpBol = simpBol;
    }
}
