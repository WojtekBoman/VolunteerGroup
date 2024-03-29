package ztw.bs5.PsiPatrol.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "uzytkownik")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Uzytkownik {

    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @Column(name = "haslo", nullable = false, length = 255)
    private String haslo;


    @Column(name = "imie", nullable = false, length = 50)
    private String imie;


    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;

    @JsonIgnore
    @OneToMany(mappedBy = "emailAdresata", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Wiadomosc> maileAdresata;

    @JsonIgnore
    @OneToMany(mappedBy = "emailNadawcy", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Wiadomosc> maileNadawcy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }


    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Set<Wiadomosc> getMaileAdresata() {
        return maileAdresata;
    }

    public void setMaileAdresata(Set<Wiadomosc> maileAdresata) {
        this.maileAdresata = maileAdresata;
    }

    public Set<Wiadomosc> getMaileNadawcy() {
        return maileNadawcy;
    }

    public void setMaileNadawcy(Set<Wiadomosc> maileNadawcy) {
        this.maileNadawcy = maileNadawcy;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Uzytkownik() {
    }


    public Uzytkownik(String email, String haslo, String imie, String nazwisko){
        this.email=email;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
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

    public void addRole(Role tempRole) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(tempRole);
        //tempRole.setUser(this.getEmail());
    }
}
