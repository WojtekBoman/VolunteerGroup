package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;

import java.util.List;

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
        return wolontariusz.getWydarzenia();
    }

    @Override
    public boolean isFull(Wydarzenie wydarzenie) {
        return wydarzenie.getLiczbaPotrzebnychWolontariuszy()<=wydarzenie.getLiczbaPrzypisanychWolontariuszy();
    }

}
