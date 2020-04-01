package ztw.bs5.PsiPatrol.Payload.Request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String haslo;

    public String getEmail() {
        return email;
    }

    public void setEmial(String emial) {
        this.email = emial;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
