package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pracownikschroniska", schema = "psipatrol", catalog = "")
public class Pracownikschroniska implements Serializable {
    private String nazwaSchroniska;
    private Uzytkownik uzytkownikEmail;
    private Set<Oferta> oferta;
    private Set<Zbiorka> zbiorka;

    @Basic
    @Column(name = "nazwaSchroniska", nullable = false, length = 50)
    public String getNazwaSchroniska() {
        return nazwaSchroniska;
    }

    public void setNazwaSchroniska(String nazwaSchroniska) {
        this.nazwaSchroniska = nazwaSchroniska;
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


//    @OneToMany(mappedBy = "idPracownika", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//            CascadeType.REFRESH})
//    public Set<Oferta> getOferta() {
//        return oferta;
//    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    }

    @OneToMany(mappedBy = "idPracownika", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    public Set<Zbiorka> getZbiorka() {
        return zbiorka;
    }

    public void setZbiorka(Set<Zbiorka> zbiorka) {
        this.zbiorka = zbiorka;
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
