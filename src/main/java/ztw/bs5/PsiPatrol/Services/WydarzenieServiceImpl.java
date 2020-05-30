package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;
import ztw.bs5.PsiPatrol.Model.MonthsResponse;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class WydarzenieServiceImpl implements WydarzenieService {


    @Autowired
    WydarzenieRepository wydarzenieRepository;

    @Override
    public List<Wydarzenie> getAllWydarzenia() {
        return wydarzenieRepository.findAll();
    }

    @Override
    public List<Wydarzenie> getWydarzeniaWolontariusza(Wolontariusz wolontariusz) {
        return new ArrayList<>(wolontariusz.getWydarzenia());
    }

    @Override
    public boolean isFull(Wydarzenie wydarzenie) {
        return wydarzenie.getLiczbaPotrzebnychWolontariuszy() <= wydarzenie.getLiczbaPrzypisanychWolontariuszy();
    }


    @Override
    public List<Wydarzenie> getFilteredWydarzeniaList(String name, String place, String category, String beginDateStr, String endDateStr, boolean onlyAvailable) {

        LocalDate beginDateLocal = LocalDate.parse(beginDateStr);
        LocalDate endDateLocal = LocalDate.parse(endDateStr);
        Date beginDate = Date.from(beginDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Wydarzenie> wydarzeniaList = new ArrayList<>(wydarzenieRepository.findAll());
        List<Wydarzenie> filteredNames = wydarzeniaList.stream().filter(item -> item.getNazwa().toLowerCase().indexOf(name.toLowerCase()) != -1).collect(Collectors.toList());
        List<Wydarzenie> filteredPlaces = filteredNames.stream().filter(item -> item.getMiejsce().toLowerCase().indexOf(place.toLowerCase()) != -1).collect(Collectors.toList());
        List<Wydarzenie> filteredCategory = filteredPlaces.stream().filter(item -> item.getKategoria().name().toLowerCase().indexOf(category.toLowerCase()) != -1).collect(Collectors.toList());
        List<Wydarzenie> filteredBeginDate = filteredCategory.stream().filter(item -> (!item.getDataRozpoczecia().before(beginDate))).collect(Collectors.toList());
        List<Wydarzenie> filteredEndDate = filteredBeginDate.stream().filter(item -> (!item.getDataRozpoczecia().after(endDate))).collect(Collectors.toList());
        List<Wydarzenie> filteredIsFull = filteredEndDate.stream().filter(item -> (item.isCzyPelne()!=onlyAvailable || !item.isCzyPelne())).collect(Collectors.toList());

        return filteredIsFull;
    }

    @Override
    public MonthsResponse getEventsNumberByMonth(int year) {
        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());

        Date date1 = Date.from(dateStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(dateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Wydarzenie> wydarzenieList = wydarzenieRepository.findAllByDataRozpoczeciaGreaterThanEqualAndDataRozpoczeciaLessThanEqual(date1,date2);
        if (wydarzenieList.isEmpty())
            return new MonthsResponse();

        int styczen = 0, luty = 0, marzec = 0, kwiecien = 0, maj = 0, czerwiec = 0,
                lipiec = 0, sierpien = 0, wrzesien = 0, pazdziernik = 0, listopad = 0, grudzien = 0;

        for (int i = 0; i < wydarzenieList.size(); i++) {
            switch (wydarzenieList.get(i).getDataRozpoczecia().getMonth()) {
                case 1:
                    styczen++;
                    break;
                case 2:
                    luty++;
                    break;
                case 3:
                    marzec++;
                    break;
                case 4:
                    kwiecien++;
                    break;
                case 5:
                    maj++;
                    break;
                case 6:
                    czerwiec++;
                    break;
                case 7:
                    lipiec++;
                    break;
                case 8:
                    sierpien++;
                    break;
                case 9:
                    wrzesien++;
                    break;
                case 10:
                    pazdziernik++;
                    break;
                case 11:
                    listopad++;
                    break;
                case 12:
                    grudzien++;
                    break;
                default:
                    break;

            }


        }

        MonthsResponse miesiace = new MonthsResponse(styczen,luty,marzec,kwiecien,maj,czerwiec,
                lipiec,sierpien,wrzesien,pazdziernik,listopad,grudzien);

        return miesiace;
    }


}
