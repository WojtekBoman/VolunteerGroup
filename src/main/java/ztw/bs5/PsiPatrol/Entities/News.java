package ztw.bs5.PsiPatrol.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "psipatrol", catalog = "")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_newsa", nullable = false)
    private int idNewsa;

    @Basic
    @Column(name = "naglowek", nullable = false)
    private String naglowek;

    @Basic
    @Column(name = "tresc", nullable = false)
    private String tresc;

    @Basic
    @Column(name = "data_dodania", nullable = false)
    private LocalDate dataDodania;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_autora", nullable = false)
    private Przewodniczacy idAutora;


    public News() {
    }

    public News(String naglowek, String tresc, LocalDate dataDodania) {
        this.naglowek=naglowek;
        this.tresc=tresc;
        this.dataDodania=dataDodania;
    }

    public int getIdNewsa() {
        return idNewsa;
    }

    public void setIdNewsa(int idNewsa) {
        this.idNewsa = idNewsa;
    }

    public String getNaglowek() {
        return naglowek;
    }

    public void setNaglowek(String naglowek) {
        this.naglowek = naglowek;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public LocalDate getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(LocalDate dataDodania) {
        this.dataDodania = dataDodania;
    }

    public Przewodniczacy getIdAutora() {
        return idAutora;
    }

    public void setIdAutora(Przewodniczacy idAutora) {
        this.idAutora = idAutora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return idNewsa == news.idNewsa &&
                Objects.equals(naglowek, news.naglowek) &&
                Objects.equals(tresc, news.tresc) &&
                Objects.equals(dataDodania, news.dataDodania) &&
                Objects.equals(idAutora, news.idAutora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNewsa, naglowek, tresc, dataDodania, idAutora);
    }
}
