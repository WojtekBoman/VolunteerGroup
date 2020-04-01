package ztw.bs5.PsiPatrol.Payload.Response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String imie;
    private String nazwisko;
    private List<String> roles;

    public JwtResponse(String token, String email, String imie, String nazwisko, List<String> roles) {
        this.token = token;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
