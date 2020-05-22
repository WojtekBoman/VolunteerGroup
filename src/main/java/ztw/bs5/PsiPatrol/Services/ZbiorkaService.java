package ztw.bs5.PsiPatrol.Services;


import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;

import java.util.List;

public interface ZbiorkaService {
    List<Zbiorka> getFilteredZbiorkaList(String title);
//    List<Zbiorka> getFilteredZbiorkaList(String title, String description, String endDate1, String endDate2, double amountNeeded);
}
