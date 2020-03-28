package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "zbiorka", schema = "psipatrol", catalog = "")
public class Zbiorka {
    private int idZbiorki;
    private String tytul;
    private String opis;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private double kwotaPotrzebna;
    private double kwotaZebrana;
    private String idPracownika;

    @Id
    @Column(name = "idZbiorki", nullable = false)
    public int getIdZbiorki() {
        return idZbiorki;
    }

    public void setIdZbiorki(int idZbiorki) {
        this.idZbiorki = idZbiorki;
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
    @Column(name = "dataRozpoczecia", nullable = false)
    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    @Basic
    @Column(name = "dataZakonczenia", nullable = false)
    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    @Basic
    @Column(name = "kwotaPotrzebna", nullable = false, precision = 0)
    public double getKwotaPotrzebna() {
        return kwotaPotrzebna;
    }

    public void setKwotaPotrzebna(double kwotaPotrzebna) {
        this.kwotaPotrzebna = kwotaPotrzebna;
    }

    @Basic
    @Column(name = "kwotaZebrana", nullable = false, precision = 0)
    public double getKwotaZebrana() {
        return kwotaZebrana;
    }

    public void setKwotaZebrana(double kwotaZebrana) {
        this.kwotaZebrana = kwotaZebrana;
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
        Zbiorka that = (Zbiorka) o;
        return idZbiorki == that.idZbiorki &&
                Double.compare(that.kwotaPotrzebna, kwotaPotrzebna) == 0 &&
                Double.compare(that.kwotaZebrana, kwotaZebrana) == 0 &&
                Objects.equals(tytul, that.tytul) &&
                Objects.equals(opis, that.opis) &&
                Objects.equals(dataRozpoczecia, that.dataRozpoczecia) &&
                Objects.equals(dataZakonczenia, that.dataZakonczenia) &&
                Objects.equals(idPracownika, that.idPracownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZbiorki, tytul, opis, dataRozpoczecia, dataZakonczenia, kwotaPotrzebna, kwotaZebrana, idPracownika);
    }
}
