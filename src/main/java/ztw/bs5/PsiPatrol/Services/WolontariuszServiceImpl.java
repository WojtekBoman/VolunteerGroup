package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WolontariuszRepository;

import java.util.List;

@Service
public class WolontariuszServiceImpl implements WolontariuszService {

    @Autowired
    WydarzenieServiceImpl wydarzenieService;

    @Autowired
    WolontariuszRepository wolontariuszRepository;

    @Override
    public void changeActivityPercentage(Wolontariusz wolontariusz) {
        List<Wydarzenie> allWydarzenia = wydarzenieService.getAllWydarzenia();
        List<Wydarzenie> wydarzeniaWolontariusza = wydarzenieService.getWydarzeniaWolontariusza(wolontariusz);

        double percentage = (double) wydarzeniaWolontariusza.size() / allWydarzenia.size();

        wolontariusz.setProcentowaAktywnosc(percentage);

    }

    @Override
    public void changeWolontariuszRank(Wolontariusz wolontariusz) {
        double activityPercentage = wolontariusz.getProcentowaAktywnosc();

        if (activityPercentage >= 0.5) {
            wolontariusz.setStopien(Wolontariusz.Stopien.KlasaSwiatowa);
        } else if (activityPercentage >= 0.3) {
            wolontariusz.setStopien(Wolontariusz.Stopien.Zawodowiec);
        } else {
            wolontariusz.setStopien(Wolontariusz.Stopien.Amator);
        }
    }

    @Override
    public void updateWolonariszStats(Wolontariusz wolontariusz) {
        changeActivityPercentage(wolontariusz);
        changeWolontariuszRank(wolontariusz);
        wolontariuszRepository.save(wolontariusz);
    }

    @Override
    public void updateAllWolonariszStats() {
        List<Wolontariusz> wolontariusz = wolontariuszRepository.findAll();
        for (Wolontariusz value : wolontariusz) {
            updateWolonariszStats(value);
        }
    }

    @Override
    public boolean isAssigned(Wolontariusz wolontariusz, Wydarzenie wydarzenie) {
        return wydarzenie.getWolontariusze().contains(wolontariusz);
    }

}
