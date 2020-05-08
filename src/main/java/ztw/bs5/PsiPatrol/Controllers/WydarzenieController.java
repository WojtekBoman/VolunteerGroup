package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.Przewodniczacy;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.PrzewodniczacyRepository;
import ztw.bs5.PsiPatrol.Repositories.WolontariuszRepository;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;
import ztw.bs5.PsiPatrol.Services.UzytkownikService;
import ztw.bs5.PsiPatrol.Services.WolontariuszService;
import ztw.bs5.PsiPatrol.Services.WydarzenieService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class WydarzenieController {


    @Autowired
    private WydarzenieService wydarzenieService;

    @Autowired
    private WolontariuszService wolontariuszService;

    @Autowired
    private WydarzenieRepository wydarzenieRepository;

    @Autowired
    private WolontariuszRepository wolontariuszRepository;

    @Autowired
    private PrzewodniczacyRepository przewodniczacyRepository;

    @GetMapping("/wydarzenia")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wydarzenie>> getAllWydarzenia() {
        try {
            List<Wydarzenie> wydarzenia = new ArrayList<>(wydarzenieRepository.findAll());

            if (wydarzenia.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wydarzenia, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wydarzenia/{id}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<Wydarzenie> getWydarzenieById(@PathVariable("id") int id) {
        Optional<Wydarzenie> wydarzenie = wydarzenieRepository.findById(id);

        if (wydarzenie.isPresent()) {
            return new ResponseEntity<>(wydarzenie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/wydarzenia")
    @PreAuthorize("hasRole('ROLE_PRZEWODNICZACY')")
    public ResponseEntity<Wydarzenie> createWydarzenie(@RequestBody Wydarzenie wydarzenie){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Przewodniczacy> przewod = przewodniczacyRepository.findById(email);
        Przewodniczacy przewodniczacy = przewod.get();//isPresent

        //zwiększenie liczby wydarzeń
        int liczbaWydarzen = przewodniczacy.getLiczbaZorganizowanychWyd();
        liczbaWydarzen++;
        przewodniczacy.setLiczbaZorganizowanychWyd(liczbaWydarzen);

        try {
            Wydarzenie tempWydarzenie = new Wydarzenie(wydarzenie.getNazwa(),wydarzenie.getMiejsce(),wydarzenie.getAdres(),wydarzenie.getOpis(),
                                                     wydarzenie.getLiczbaPotrzebnychWolontariuszy(), wydarzenie.getKategoria(), wydarzenie.getDataRozpoczecia());
            tempWydarzenie.setIdTworcy(przewodniczacy);
            Wydarzenie wydarzenieToSave = wydarzenieRepository.save(tempWydarzenie);
            wolontariuszService.updateAllWolonariszStats();
            return new ResponseEntity<>(wydarzenieToSave, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/wydarzenia/filtered")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> getFilteredWydarzenia(@RequestParam(required=false, name="name", defaultValue = "") String name,
                                                                  @RequestParam(required=false, name="place",defaultValue = "") String place,
                                                                  @RequestParam(required=false, name="category",defaultValue = "") String category,
                                                                  @RequestParam(required=false, name="beginDate",defaultValue = "1000-01-01") String beginDate,
                                                                  @RequestParam(required=false, name="endDate",defaultValue = "3000-01-01") String endDate,
                                                                  @RequestParam(required=false, name="onlyAvailable",defaultValue = "false") String onlyAvailable, Pageable pageable) {
        System.out.println("Kontroler");
        try {
            List<Wydarzenie> wydarzenia = new ArrayList<>(wydarzenieService.getFilteredWydarzeniaList(name, place,category, beginDate, endDate,Boolean.parseBoolean(onlyAvailable)));

            if (wydarzenia.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            int start = (int)pageable.getOffset();
            int end = (start + pageable.getPageSize()) > wydarzenia.size() ? wydarzenia.size() : (start + pageable.getPageSize());
            Page<Wydarzenie> pages = new PageImpl<Wydarzenie>(wydarzenia.subList(start, end), pageable, wydarzenia.size());

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

