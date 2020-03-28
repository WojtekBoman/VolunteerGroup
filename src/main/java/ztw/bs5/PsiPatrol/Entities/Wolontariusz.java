package ztw.bs5.PsiPatrol.Entities;

import ztw.bs5.PsiPatrol.Entities.Enums.Stopien;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wolontariusz", schema = "psipatrol", catalog = "")
public class Wolontariusz {
    private Stopien stopien;
    private double procentowaAktywnosc;
    private String uzytkownikEmail;

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
    @Column(name = "uzytkownikEmail", nullable = false, length = 50)
    public String getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(String uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
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
