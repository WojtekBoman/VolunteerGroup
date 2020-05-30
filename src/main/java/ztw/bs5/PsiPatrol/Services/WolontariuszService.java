package ztw.bs5.PsiPatrol.Services;

import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;

import java.util.List;

public interface WolontariuszService {
     void changeActivityPercentage(Wolontariusz wolontariusz);
     void changeWolontariuszRank(Wolontariusz wolontariusz);
     void updateWolonariszStats(Wolontariusz wolontariusz);
     void updateAllWolonariszStats();
     boolean isAssigned(Wolontariusz wolontariusz, Wydarzenie wydarzenie);
     List<Wolontariusz> getMostActiveUsers(int usersNumber);
}
