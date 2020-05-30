package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WolontariuszRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WolontariuszServiceImpl implements WolontariuszService {

    @Autowired
    WydarzenieServiceImpl wydarzenieService;

    @Autowired
    WolontariuszRepository wolontariuszRepository;

    @Override
    public void changeActivityPercentage(Wolontariusz wolontariusz) {
        List<Wydarzenie> allWydarzenia = new ArrayList<>(wydarzenieService.getAllWydarzenia());
        List<Wydarzenie> wydarzeniaWolontariusza = new ArrayList<>(wydarzenieService.getWydarzeniaWolontariusza(wolontariusz));

        double percentage = (double) wydarzeniaWolontariusza.size() / (double) allWydarzenia.size();

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
        List<Wolontariusz> wolontariusz = new ArrayList<>(wolontariuszRepository.findAll());
        for (Wolontariusz value : wolontariusz) {
            updateWolonariszStats(value);
        }
    }

    @Override
    public boolean isAssigned(Wolontariusz wolontariusz, Wydarzenie wydarzenie) {
        return wydarzenie.getWolontariusze().contains(wolontariusz);
    }

    @Override
    public List<Wolontariusz> getMostActiveUsers(int usersNumber) {
        List<Wolontariusz> wolontariuszList = new ArrayList<>(wolontariuszRepository.findAllByOrderByProcentowaAktywnoscDesc());
        List<Wolontariusz> wolontariuszTopNList = wolontariuszList.stream().limit(usersNumber).collect(Collectors.toList());

        return wolontariuszTopNList;
    }

}
