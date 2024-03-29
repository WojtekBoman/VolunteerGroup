package ztw.bs5.PsiPatrol.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "zbiorka", schema = "psipatrol", catalog = "")
public class Zbiorka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zbiorki", nullable = false)
    private int idZbiorki;

    @Basic
    @Column(name = "tytul", nullable = false, length = 50)
    private String tytul;

    @Basic
    @Column(name = "opis", nullable = false, length = 255)
    private String opis;

    @Basic
    @Column(name = "data_rozpoczecia", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataRozpoczecia;

    @Basic
    @Column(name = "data_zakonczenia", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataZakonczenia;

    @Basic
    @Column(name = "kwota_potrzebna", nullable = false, precision = 0)
    private double kwotaPotrzebna;

    @Basic
    @Column(name = "kwota_zebrana", nullable = false, precision = 0)
    private double kwotaZebrana;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_pracownika", nullable = false)
    private Pracownikschroniska idPracownika;

    public Zbiorka() {
    }
    public Zbiorka(String tytul, String opis, LocalDate dataRozpoczecia, LocalDate dataZakonczenia, double kwotaPotrzebna) {
        this.tytul=tytul;
        this.opis=opis;
        this.dataRozpoczecia=dataRozpoczecia;
        this.dataZakonczenia=dataZakonczenia;
        this.kwotaPotrzebna=kwotaPotrzebna;
        this.kwotaZebrana=0;
    }

    public int getIdZbiorki() {
        return idZbiorki;
    }

    public void setIdZbiorki(int idZbiorki) {
        this.idZbiorki = idZbiorki;
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


    public LocalDate getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }


    public LocalDate getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(LocalDate dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }


    public double getKwotaPotrzebna() {
        return kwotaPotrzebna;
    }

    public void setKwotaPotrzebna(double kwotaPotrzebna) {
        this.kwotaPotrzebna = kwotaPotrzebna;
    }


    public double getKwotaZebrana() {
        return kwotaZebrana;
    }

    public void setKwotaZebrana(double kwotaZebrana) {
        this.kwotaZebrana = kwotaZebrana;
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
