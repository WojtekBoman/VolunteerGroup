package ztw.bs5.PsiPatrol.Entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @Basic
    @Column(name = "zdjecie", nullable = true, length = 255)
    private String zdjecie;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pracownika", nullable = false)
    private Pracownikschroniska idPracownika;

    public Oferta() {
    }

    public Oferta(String tytul, String opis, String imie) {
        this.tytul = tytul;
        this.opis = opis;
        this.imie = imie;
    }

    public Oferta(String tytul, String opis, String imie, String zdjecie) {
        this.tytul = tytul;
        this.opis = opis;
        this.imie = imie;
        this.zdjecie=zdjecie;
    }



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

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

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