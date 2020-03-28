package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "udzial", schema = "psipatrol", catalog = "")
@IdClass(UdzialPK.class)
public class Udzial {
    private String emailUzytkownika;
    private int idZdarzenia;

    @Id
    @Column(name = "emailUzytkownika", nullable = false, length = 50)
    public String getEmailUzytkownika() {
        return emailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        this.emailUzytkownika = emailUzytkownika;
    }

    @Id
    @Column(name = "idZdarzenia", nullable = false)
    public int getIdZdarzenia() {
        return idZdarzenia;
    }

    public void setIdZdarzenia(int idZdarzenia) {
        this.idZdarzenia = idZdarzenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Udzial that = (Udzial) o;
        return idZdarzenia == that.idZdarzenia &&
                Objects.equals(emailUzytkownika, that.emailUzytkownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailUzytkownika, idZdarzenia);
    }
}
