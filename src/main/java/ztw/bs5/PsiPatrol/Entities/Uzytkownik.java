package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "uzytkownik", schema = "psipatrol", catalog = "")
public class Uzytkownik {
    private String email;
    private String haslo;
    private String imie;
    private String nazwisko;
    private Przewodniczacy przewodniczacy;
    private Wolontariusz wolontariusz;
    private Pracownikschroniska pracownikschroniska;
    private Set<Wiadomosc> maileAdresata;
    private Set<Wiadomosc> maileNadawcy;


    @Id
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "haslo", nullable = false, length = 255)
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 50)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 50)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @OneToOne(mappedBy="uzytkownikEmail",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    public Przewodniczacy getPrzewodniczacy() {
        return przewodniczacy;
    }

    public void setPrzewodniczacy(Przewodniczacy przewodniczacy) {
        this.przewodniczacy = przewodniczacy;
    }

    @OneToOne(mappedBy="uzytkownikEmail",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    public Wolontariusz getWolontariusz() {
        return wolontariusz;
    }

    public void setWolontariusz(Wolontariusz wolontariusz) {
        this.wolontariusz = wolontariusz;
    }

    @OneToOne(mappedBy="uzytkownikEmail",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    public Pracownikschroniska getPracownikschroniska() {
        return pracownikschroniska;
    }

    public void setPracownikschroniska(Pracownikschroniska pracownikschroniska) {
        this.pracownikschroniska = pracownikschroniska;
    }

    @OneToMany(mappedBy = "emailAdresata", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    public Set<Wiadomosc> getMaileAdresata() {
        return maileAdresata;
    }

    public void setMaileAdresata(Set<Wiadomosc> maileAdresata) {
        this.maileAdresata = maileAdresata;
    }

    @OneToMany(mappedBy = "emailNadawcy", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH})
    public Set<Wiadomosc> getMaileNadawcy() {
        return maileNadawcy;
    }

    public void setMaileNadawcy(Set<Wiadomosc> maileNadawcy) {
        this.maileNadawcy = maileNadawcy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzytkownik that = (Uzytkownik) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(haslo, that.haslo) &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, haslo, imie, nazwisko);
    }
}
