package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
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


}
