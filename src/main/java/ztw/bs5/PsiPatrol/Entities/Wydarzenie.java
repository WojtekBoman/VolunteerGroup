package ztw.bs5.PsiPatrol.Entities;

import ztw.bs5.PsiPatrol.Entities.Enums.Kategoria;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "wydarzenie", schema = "psipatrol", catalog = "")
public class Wydarzenie {
    private int idZdarzenia;
    private String nazwa;
    private int liczbaPotrzebnychWolontariuszy;
    private Kategoria kategoria;
    private Date dataRozpoczecia;
    private String idTworcy;

    @Id
    @Column(name = "idZdarzenia", nullable = false)
    public int getIdZdarzenia() {
        return idZdarzenia;
    }

    public void setIdZdarzenia(int idZdarzenia) {
        this.idZdarzenia = idZdarzenia;
    }

    @Basic
    @Column(name = "nazwa", nullable = false, length = 50)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "liczbaPotrzebnychWolontariuszy", nullable = false)
    public int getLiczbaPotrzebnychWolontariuszy() {
        return liczbaPotrzebnychWolontariuszy;
    }

    public void setLiczbaPotrzebnychWolontariuszy(int liczbaPotrzebnychWolontariuszy) {
        this.liczbaPotrzebnychWolontariuszy = liczbaPotrzebnychWolontariuszy;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "kategoria", nullable = false)
    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
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
    @Column(name = "idTworcy", nullable = false, length = 50)
    public String getIdTworcy() {
        return idTworcy;
    }

    public void setIdTworcy(String idTworcy) {
        this.idTworcy = idTworcy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wydarzenie that = (Wydarzenie) o;
        return idZdarzenia == that.idZdarzenia &&
                liczbaPotrzebnychWolontariuszy == that.liczbaPotrzebnychWolontariuszy &&
                Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(kategoria, that.kategoria) &&
                Objects.equals(dataRozpoczecia, that.dataRozpoczecia) &&
                Objects.equals(idTworcy, that.idTworcy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZdarzenia, nazwa, liczbaPotrzebnychWolontariuszy, kategoria, dataRozpoczecia, idTworcy);
    }
}
