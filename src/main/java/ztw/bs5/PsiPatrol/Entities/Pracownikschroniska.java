package ztw.bs5.PsiPatrol.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pracownikschroniska")
@PrimaryKeyJoinColumn(name = "uzytkownik_email")
public class Pracownikschroniska extends Uzytkownik implements Serializable {


    @Column(name = "nazwa_schroniska", nullable = false, length = 50)
    private String nazwaSchroniska;

    @JsonIgnore
    @OneToMany(mappedBy = "idPracownika", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    private Set<Oferta> oferta;

    @JsonIgnore
    @OneToMany(mappedBy = "idPracownika", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    private Set<Zbiorka> zbiorka;


    public String getNazwaSchroniska() {
        return nazwaSchroniska;
    }

    public void setNazwaSchroniska(String nazwaSchroniska) {
        this.nazwaSchroniska = nazwaSchroniska;
    }

    public Set<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    }


    public Pracownikschroniska() {
    }

    public Pracownikschroniska(String email, String haslo, String imie, String nazwisko, String nazwaSchroniska) {
        super(email, haslo, imie, nazwisko);
        this.nazwaSchroniska = nazwaSchroniska;
    }

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
        return Objects.equals(nazwaSchroniska, that.nazwaSchroniska);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwaSchroniska);
    }
}
