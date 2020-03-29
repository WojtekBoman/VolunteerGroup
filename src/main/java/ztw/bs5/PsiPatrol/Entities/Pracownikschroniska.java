package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pracownikschroniska", schema = "psipatrol", catalog = "")
public class Pracownikschroniska implements Serializable {

    @Basic
    @Column(name = "nazwa_schroniska", nullable = false, length = 50)
    private String nazwaSchroniska;

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uzytkownik_email", nullable = false)
    private Uzytkownik uzytkownikEmail;

//    @OneToMany(mappedBy = "idPracownika", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//            CascadeType.REFRESH})
//    private Set<Oferta> oferta;

    @OneToMany(mappedBy = "idPracownika", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    private Set<Zbiorka> zbiorka;


    public String getNazwaSchroniska() {
        return nazwaSchroniska;
    }

    public void setNazwaSchroniska(String nazwaSchroniska) {
        this.nazwaSchroniska = nazwaSchroniska;
    }


    public Uzytkownik getUzytkownikEmail() {
        return uzytkownikEmail;
    }

    public void setUzytkownikEmail(Uzytkownik uzytkownikEmail) {
        this.uzytkownikEmail = uzytkownikEmail;
    }



//    public Set<Oferta> getOferta() {
//        return oferta;
//    }
//
//    public void setOferta(Set<Oferta> oferta) {
//        this.oferta = oferta;
//    }


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
