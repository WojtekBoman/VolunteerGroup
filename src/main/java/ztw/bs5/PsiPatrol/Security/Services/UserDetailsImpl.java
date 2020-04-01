package ztw.bs5.PsiPatrol.Security.Services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;



    private String email;
    private String imie;
    private String nazwisko;

    @JsonIgnore//dobry?
    private String haslo;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String haslo, String imie, String nazwisko,
                           Collection<? extends GrantedAuthority>authorities) {

        this.email = email;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;

        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Uzytkownik user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getEmail(),
                user.getHaslo(),
                user.getImie(),
                user.getNazwisko(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return haslo;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(email, user.email);
    }
}
