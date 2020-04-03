package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "przewodniczacy")
@PrimaryKeyJoinColumn(name = "uzytkownik_email")
public class Przewodniczacy extends Uzytkownik implements Serializable {


    @Column(name = "liczba_zorganizowanych_wyd", nullable = false)
    private int liczbaZorganizowanychWyd;


    @OneToMany(mappedBy = "idTworcy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Wydarzenie> wydarzenia;


    public int getLiczbaZorganizowanychWyd() {
        return liczbaZorganizowanychWyd;
    }

    public void setLiczbaZorganizowanychWyd(int liczbaZorganizowanychWyd) {
        this.liczbaZorganizowanychWyd = liczbaZorganizowanychWyd;
    }


    public Set<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(Set<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    public Przewodniczacy() {
    }

    public Przewodniczacy(String email, String haslo, String imie, String nazwisko, int liczbaZorganizowanychWyd) {
        super(email, haslo, imie, nazwisko);
        this.liczbaZorganizowanychWyd=liczbaZorganizowanychWyd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Przewodniczacy that = (Przewodniczacy) o;
        return liczbaZorganizowanychWyd == that.liczbaZorganizowanychWyd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(liczbaZorganizowanychWyd);
    }
}
