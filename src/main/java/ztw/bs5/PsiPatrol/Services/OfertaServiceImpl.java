package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    OfertaRepository ofertaRepository;

    @Override
    public List<Oferta> getFilteredOfertaList(String title, String name) {
        List<Oferta> ofertaList = new ArrayList<>(ofertaRepository.findAll());


        List<Oferta> filteredTitles = ofertaList.stream().filter(item -> item.getTytul().toLowerCase().indexOf(title.toLowerCase()) != -1).collect(Collectors.toList());
        List<Oferta> filteredNames = filteredTitles.stream().filter(item -> item.getImie().toLowerCase().indexOf(name.toLowerCase()) != -1).collect(Collectors.toList());
        return filteredNames;
    }
}
