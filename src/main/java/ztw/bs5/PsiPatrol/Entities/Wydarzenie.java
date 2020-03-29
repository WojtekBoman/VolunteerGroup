package ztw.bs5.PsiPatrol.Entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wydarzenie", schema = "psipatrol", catalog = "")
public class Wydarzenie {

    public enum Kategoria {
        Sprzatanie("Sprzatanie"),
        Inne("Inne");


        private String value;

        Kategoria(String value){this.value = value;}

        public String getValue() {return value;}
    }

    @Id
    @Column(name = "id_zdarzenia", nullable = false)
    private int idZdarzenia;

    @Basic
    @Column(name = "nazwa", nullable = false, length = 50)
    private String nazwa;

    @Basic
    @Column(name = "liczba_potrzebnych_wolontariuszy", nullable = false)
    private int liczbaPotrzebnychWolontariuszy;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "kategoria", nullable = false)
    private Kategoria kategoria;

    @Basic
    @Column(name = "data_rozpoczecia", nullable = false)
    private Date dataRozpoczecia;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_tworcy", nullable = false)
    private Przewodniczacy idTworcy;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH,},fetch = FetchType.EAGER)
    @JoinTable(name="Udzial",
            joinColumns=@JoinColumn(name="id_zdarzenia"),
            inverseJoinColumns=@JoinColumn(name="email_uzytkownika"))
    private List<Wolontariusz> wolontariusze;

    public Wydarzenie() {
    }

    public Wydarzenie(int idZdarzenia, String nazwa, int liczbaPotrzebnychWolontariuszy, String kategoria, Date dataRozpoczecia) {
        this.idZdarzenia=idZdarzenia;
        this.nazwa=nazwa;
        this.liczbaPotrzebnychWolontariuszy=liczbaPotrzebnychWolontariuszy;
        this.kategoria=Kategoria.valueOf(kategoria);
        this.dataRozpoczecia=dataRozpoczecia;
    }


    public int getIdZdarzenia() {
        return idZdarzenia;
    }

    public void setIdZdarzenia(int idZdarzenia) {
        this.idZdarzenia = idZdarzenia;
    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


    public int getLiczbaPotrzebnychWolontariuszy() {
        return liczbaPotrzebnychWolontariuszy;
    }

    public void setLiczbaPotrzebnychWolontariuszy(int liczbaPotrzebnychWolontariuszy) {
        this.liczbaPotrzebnychWolontariuszy = liczbaPotrzebnychWolontariuszy;
    }


    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }


    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }



    public Przewodniczacy getIdTworcy() {
        return idTworcy;
    }

    public void setIdTworcy(Przewodniczacy idTworcy) {
        this.idTworcy = idTworcy;
    }



    public List<Wolontariusz> getWolontariusze() {
        return wolontariusze;
    }

    public void setWolontariusze(List<Wolontariusz> wolontariusze) {
        this.wolontariusze = wolontariusze;
    }

    public void addWolontariusz(Wolontariusz wolontariusz) {
        if (wolontariusze==null) {
            wolontariusze = new ArrayList<>();
        }

        wolontariusze.add(wolontariusz);
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
