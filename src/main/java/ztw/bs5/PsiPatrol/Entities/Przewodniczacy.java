package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "przewodniczacy", schema = "psipatrol", catalog = "")
public class Przewodniczacy implements Serializable {

    @Basic
    @Column(name = "liczba_zorganizowanych_wyd", nullable = false)
    private int liczbaZorganizowanychWyd;

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uzytkownik_email", nullable = false)
    private Uzytkownik uzytkownikEmail;

    @OneToMany(mappedBy = "idTworcy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Wydarzenie> wydarzenia;


    public int getLiczbaZorganizowanychWyd() {
        return liczbaZorganizowanychWyd;
    }

    public void setLiczbaZorganizowanychWyd(int liczbaZorganizowanychWyd) {
        this.liczbaZorganizowanychWyd = liczbaZorganizowanychWyd;
    }


    public Uzytkownik getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(Uzytkownik uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
    }


    public Set<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public void setWydarzenia(Set<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Przewodniczacy that = (Przewodniczacy) o;
        return liczbaZorganizowanychWyd == that.liczbaZorganizowanychWyd &&
                Objects.equals(uzytkownikEmail, that.uzytkownikEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liczbaZorganizowanychWyd, uzytkownikEmail);
    }
}
