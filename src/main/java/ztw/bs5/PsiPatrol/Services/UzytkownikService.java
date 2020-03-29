package ztw.bs5.PsiPatrol.Services;

import ztw.bs5.PsiPatrol.Entities.Uzytkownik;

import java.util.List;

public interface UzytkownikService {
    public List<Uzytkownik> getAllUzytkownicy();
    public Uzytkownik getUzytkownik(String email);
}
