package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oferta", schema = "psipatrol", catalog = "")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferty", nullable = false)
    private int idOferty;

    @Basic
    @Column(name = "tytul", nullable = false, length = 50)
    private String tytul;

    @Basic
    @Column(name = "opis", nullable = false, length = 255)
    private String opis;

    @Basic
    @Column(name = "imie", nullable = false, length = 50)
    private String imie;


    private Pracownikschroniska idPracownika;


    public int getIdOferty() {
        return idOferty;
    }

    public void setIdOferty(int idOferty) {
        this.idOferty = idOferty;
    }


    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pracownika", nullable = false)
    public Pracownikschroniska getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Pracownikschroniska idPracownika) {
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