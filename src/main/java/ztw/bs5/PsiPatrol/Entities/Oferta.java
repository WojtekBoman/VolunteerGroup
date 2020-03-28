package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oferta", schema = "psipatrol", catalog = "")
public class Oferta {
    private int idOferty;
    private String tytul;
    private String opis;
    private String imie;
    private String idPracownika;

    @Id
    @Column(name = "idOferty", nullable = false)
    public int getIdOferty() {
        return idOferty;
    }

    public void setIdOferty(int idOferty) {
        this.idOferty = idOferty;
    }

    @Basic
    @Column(name = "tytul", nullable = false, length = 50)
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Basic
    @Column(name = "opis", nullable = false, length = 255)
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 50)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "idPracownika", nullable = false, length = 50)
    public String getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(String idPracownika) {
        this.idPracownika = idPracownika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oferta that = (Oferta) o;
        return idOferty == that.idOferty &&
                Objects.equals(tytul, that.tytul) &&
                Objects.equals(opis, that.opis) &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(idPracownika, that.idPracownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOferty, tytul, opis, imie, idPracownika);
    }
}
