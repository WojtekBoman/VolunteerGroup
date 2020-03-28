package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pracownikschroniska", schema = "psipatrol", catalog = "")
public class Pracownikschroniska {
    private String nazwaSchroniska;
    private String uzytkownikEmail;

    @Basic
    @Column(name = "nazwaSchroniska", nullable = false, length = 50)
    public String getNazwaSchroniska() {
        return nazwaSchroniska;
    }

    public void setNazwaSchroniska(String nazwaSchroniska) {
        this.nazwaSchroniska = nazwaSchroniska;
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
        Pracownikschroniska that = (Pracownikschroniska) o;
        return Objects.equals(nazwaSchroniska, that.nazwaSchroniska) &&
                Objects.equals(uzytkownikEmail, that.uzytkownikEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwaSchroniska, uzytkownikEmail);
    }
}
