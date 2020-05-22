package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;
import ztw.bs5.PsiPatrol.Repositories.ZbiorkaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
