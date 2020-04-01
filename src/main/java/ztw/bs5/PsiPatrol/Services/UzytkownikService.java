package ztw.bs5.PsiPatrol.Services;

import ztw.bs5.PsiPatrol.Entities.Uzytkownik;

import java.util.List;

public interface UzytkownikService {
     List<Uzytkownik> getAllUzytkownicy();
     Uzytkownik getUzytkownik(String email);
}
