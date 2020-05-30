package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;
import ztw.bs5.PsiPatrol.Model.MonthsResponse;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;
import ztw.bs5.PsiPatrol.Repositories.ZbiorkaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class ZbiorkaServiceImpl implements ZbiorkaService {

    @Autowired
    ZbiorkaRepository zbiorkaRepository;

    @Override
    public List<Zbiorka> getFilteredZbiorkaList(String title) {
        List<Zbiorka> zbiorkaList = new ArrayList<>(zbiorkaRepository.findAll());

        List<Zbiorka> filteredTitles = zbiorkaList.stream().filter(item -> item.getTytul().toLowerCase().indexOf(title.toLowerCase()) != -1).collect(Collectors.toList());
//        List<Zbiorka> filteredDescriptions = filteredTitles.stream().filter(item -> item.getOpis().toLowerCase().indexOf(description.toLowerCase()) != -1).collect(Collectors.toList());

        return filteredTitles;
    }

    @Override
    public MonthsResponse getCollecionNumberByMonth(int year) {
        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());

        List<Zbiorka> zbiorkaList = zbiorkaRepository.findAllByDataRozpoczeciaGreaterThanEqualAndDataRozpoczeciaIsLessThanEqual(dateStart, dateEnd);
        if (zbiorkaList.isEmpty())
                return new MonthsResponse();

        int styczen = 0, luty = 0, marzec = 0, kwiecien = 0, maj = 0, czerwiec = 0,
            lipiec = 0, sierpien = 0, wrzesien = 0, pazdziernik = 0, listopad = 0, grudzien = 0;

        for (int i = 0; i < zbiorkaList.size(); i++) {
            switch (zbiorkaList.get(i).getDataRozpoczecia().getMonthValue()) {
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
