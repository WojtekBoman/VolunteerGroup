package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Pracownikschroniska;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;
import ztw.bs5.PsiPatrol.Repositories.PracownikschroniskaRepository;
import ztw.bs5.PsiPatrol.Repositories.WolontariuszRepository;
import ztw.bs5.PsiPatrol.Services.OfertaService;
import ztw.bs5.PsiPatrol.Services.WolontariuszService;
import ztw.bs5.PsiPatrol.Services.WydarzenieService;
import ztw.bs5.PsiPatrol.Services.ZbiorkaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StatsController {

    @Autowired
    private WolontariuszService wolontariuszService;

    @Autowired
    private ZbiorkaService zbiorkaService;

    @Autowired
    private WydarzenieService wydarzenieService;


    @GetMapping("/statystyki/aktywnosc/{liczba}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wolontariusz>> getAktywnosc(@PathVariable("liczba") int liczba) {
        try {
            List<Wolontariusz> wolontariuszList = new ArrayList<>(wolontariuszService.getMostActiveUsers(liczba));

            if (wolontariuszList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }

            return new ResponseEntity<>(wolontariuszList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/statystyki/zbiorki/{rok}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> getMiesiaceDlaZbiorek(@PathVariable("rok") int rok) {
        try {

            return new ResponseEntity<>(zbiorkaService.getCollecionNumberByMonth(rok), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/statystyki/wydarzenia/{rok}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> getMiesiaceDlaWydazren(@PathVariable("rok") int rok) {
        try {

            return new ResponseEntity<>(wydarzenieService.getEventsNumberByMonth(rok), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping("/oferty/{id}")
//    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
//    public ResponseEntity<Oferta> getOfertaById(@PathVariable("id") int id) {
//        Optional<Oferta> oferta = ofertaRepository.findById(id);
//
//        if (oferta.isPresent()) {
//            return new ResponseEntity<>(oferta.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }







}

