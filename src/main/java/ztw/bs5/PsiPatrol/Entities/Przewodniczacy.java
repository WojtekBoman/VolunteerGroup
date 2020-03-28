package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "przewodniczacy", schema = "psipatrol", catalog = "")
public class Przewodniczacy implements Serializable {
    private int liczbaZorganizowanychWyd;
    private Uzytkownik uzytkownikEmail;
    private Set<Wydarzenie> wydarzenia;

    @Basic
    @Column(name = "liczbaZorganizowanychWyd", nullable = false)
    public int getLiczbaZorganizowanychWyd() {
        return liczbaZorganizowanychWyd;
    }

    public void setLiczbaZorganizowanychWyd(int liczbaZorganizowanychWyd) {
        this.liczbaZorganizowanychWyd = liczbaZorganizowanychWyd;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uzytkownikEmail", nullable = false)
    public Uzytkownik getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(Uzytkownik uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
    }

    @OneToMany(mappedBy = "idTworcy", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
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
