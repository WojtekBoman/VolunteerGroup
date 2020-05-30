package ztw.bs5.PsiPatrol.Services;

import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Model.MonthsResponse;

import java.util.Date;
import java.util.List;

public interface WydarzenieService {
    List<Wydarzenie> getAllWydarzenia();

    List<Wydarzenie> getWydarzeniaWolontariusza(Wolontariusz wolontariusz);

    boolean isFull(Wydarzenie wydarzenie);

    List<Wydarzenie> getFilteredWydarzeniaList(String name, String place, String category, String beginDate, String endDate, boolean onlyAvailable);
    MonthsResponse getEventsNumberByMonth(int year);

}
