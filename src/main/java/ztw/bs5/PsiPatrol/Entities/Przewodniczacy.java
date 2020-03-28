package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "przewodniczacy", schema = "psipatrol", catalog = "")
public class Przewodniczacy {
    private int liczbaZorganizowanychWyd;
    private String uzytkownikEmail;

    @Basic
    @Column(name = "liczbaZorganizowanychWyd", nullable = false)
    public int getLiczbaZorganizowanychWyd() {
        return liczbaZorganizowanychWyd;
    }

    public void setLiczbaZorganizowanychWyd(int liczbaZorganizowanychWyd) {
        this.liczbaZorganizowanychWyd = liczbaZorganizowanychWyd;
    }

    @Id
    @Column(name = "uzytkownikEmail", nullable = false, length = 50)
    public String getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(String uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
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
