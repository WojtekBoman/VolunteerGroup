package ztw.bs5.PsiPatrol.Entities;

import ztw.bs5.PsiPatrol.Entities.Enums.Stopien;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wolontariusz", schema = "psipatrol", catalog = "")
public class Wolontariusz implements Serializable {
    private Stopien stopien;
    private double procentowaAktywnosc;
    private Uzytkownik uzytkownikEmail;
    private List<Wydarzenie> wydarzenia;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "stopien", nullable = false)
    public Stopien getStopien() {
        return stopien;
    }

    public void setStopien(Stopien stopien) {
        this.stopien = stopien;
    }

    @Basic
    @Column(name = "procentowaAktywnosc", nullable = false, precision = 0)
    public double getProcentowaAktywnosc() {
        return procentowaAktywnosc;
    }

    public void setProcentowaAktywnosc(double procentowaAktywnosc) {
        this.procentowaAktywnosc = procentowaAktywnosc;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uzytkownikEmail", nullable = false)
    public Uzytkownik getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(Uzytkownik uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
    }

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="Udzial",
            joinColumns=@JoinColumn(name="emailUzytkownika"),
            inverseJoinColumns=@JoinColumn(name="idZdarzenia"))
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
