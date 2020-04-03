package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wolontariusz")
@PrimaryKeyJoinColumn(name = "uzytkownik_email")
public class Wolontariusz extends Uzytkownik implements Serializable {

    public enum Stopien {
        Amator("Amator"),
        Zawodowiec("Zawodowiec"),
        KlasaSwiatowa("KlasaSwiatowa");

        private String value;

        Stopien(String value){this.value = value;}

        public String getValue() {return value;}
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "stopien", nullable = false)
    private Stopien stopien;


    @Column(name = "procentowa_aktywnosc", nullable = false, precision = 0)
    private double procentowaAktywnosc;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="Udzial",
            joinColumns=@JoinColumn(name="email_uzytkownika"),
            inverseJoinColumns=@JoinColumn(name="id_zdarzenia"))
    private List<Wydarzenie> wydarzenia;


    public Stopien getStopien() {
        return stopien;
    }

    public void setStopien(Stopien stopien) {
        this.stopien = stopien;
    }


    public double getProcentowaAktywnosc() {
        return procentowaAktywnosc;
    }

    public void setProcentowaAktywnosc(double procentowaAktywnosc) {
        this.procentowaAktywnosc = procentowaAktywnosc;
    }


    public Wolontariusz() {

    }

    public Wolontariusz(String email, String haslo, String imie, String nazwisko, String stopien, double procentowaAktywnosc){
        super(email, haslo, imie, nazwisko);
        this.stopien=Stopien.valueOf(stopien);
        this.procentowaAktywnosc=procentowaAktywnosc;
    }


    public List<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(List<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wolontariusz that = (Wolontariusz) o;
        return Double.compare(that.procentowaAktywnosc, procentowaAktywnosc) == 0 &&
                Objects.equals(stopien, that.stopien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopien, procentowaAktywnosc);
    }
}
