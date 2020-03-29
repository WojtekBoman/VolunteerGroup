package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wolontariusz", schema = "psipatrol", catalog = "")
public class Wolontariusz implements Serializable {

    public enum Stopien {
        Amator("Amator"),
        Zawodowiec("Zawodowiec"),
        KlasaSwiatowa("KlasaSwiatowa");

        private String value;

        Stopien(String value){this.value = value;}

        public String getValue() {return value;}
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "stopien", nullable = false)
    private Stopien stopien;

    @Basic
    @Column(name = "procentowa_aktywnosc", nullable = false, precision = 0)
    private double procentowaAktywnosc;

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uzytkownik_email", nullable = false)
    private Uzytkownik uzytkownikEmail;

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


    public Uzytkownik getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(Uzytkownik uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
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
                Objects.equals(stopien, that.stopien) &&
                Objects.equals(uzytkownikEmail, that.uzytkownikEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopien, procentowaAktywnosc, uzytkownikEmail);
    }
}
