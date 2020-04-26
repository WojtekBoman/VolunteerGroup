package ztw.bs5.PsiPatrol.Services;

import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;

import java.util.List;

public interface WydarzenieService {
    List<Wydarzenie> getAllWydarzenia();
    List<Wydarzenie> getWydarzeniaWolontariusza(Wolontariusz wolontariusz);
    boolean isFull(Wydarzenie wydarzenie);
}
