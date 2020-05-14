package ztw.bs5.PsiPatrol.Services;


import ztw.bs5.PsiPatrol.Entities.Oferta;

import java.util.List;

public interface OfertaService {
    List<Oferta> getFilteredOfertaList(String title, String name);
}
