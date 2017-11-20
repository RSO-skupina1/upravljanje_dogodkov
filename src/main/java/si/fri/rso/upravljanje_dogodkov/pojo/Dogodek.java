package si.fri.rso.upravljanje_dogodkov.pojo;

import java.util.Date;

public class Dogodek {

    private int id;
    private String nazivDogodka;
    private Date datumDogodka;
    private String opisDogodka;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivDogodka() {
        return nazivDogodka;
    }

    public void setNazivDogodka(String nazivDogodka) {
        this.nazivDogodka = nazivDogodka;
    }

    public Date getDatumDogodka() {
        return datumDogodka;
    }

    public void setDatumDogodka(Date datumDogodka) {
        this.datumDogodka = datumDogodka;
    }

    public String getOpisDogodka() {
        return opisDogodka;
    }

    public void setOpisDogodka(String opisDogodka) {
        this.opisDogodka = opisDogodka;
    }
}
