package main.model;

public abstract class ImenovaniEntitet {
    String naziv;
    Long id;

    /**
     * konstruktor prima naziv
     */
    public ImenovaniEntitet(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {

        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
