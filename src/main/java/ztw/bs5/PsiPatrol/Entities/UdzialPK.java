package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UdzialPK implements Serializable {
    private String emailUzytkownika;
    private int idZdarzenia;

    @NotNull
    @Column(name = "emailUzytkownika", nullable = false, length = 50)
    @Id
    public String getEmailUzytkownika() {
        return emailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        this.emailUzytkownika = emailUzytkownika;
    }

    @NotNull
    @Column(name = "idZdarzenia", nullable = false)
    @Id
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
        UdzialPK that = (UdzialPK) o;
        return idZdarzenia == that.idZdarzenia &&
                Objects.equals(emailUzytkownika, that.emailUzytkownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailUzytkownika, idZdarzenia);
    }
}
