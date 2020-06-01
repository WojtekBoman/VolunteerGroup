package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Pracownikschroniska;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;
import ztw.bs5.PsiPatrol.Repositories.PracownikschroniskaRepository;
import ztw.bs5.PsiPatrol.Services.OfertaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OfertaController {


    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PracownikschroniskaRepository pracownikschroniskaRepository;

    @GetMapping("/oferty")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Oferta>> getAllOferty() {
        try {
            List<Oferta> oferty = new ArrayList<>(ofertaRepository.findAll());

            if (oferty.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(oferty, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/oferty/{id}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<Oferta> getOfertaById(@PathVariable("id") int id) {
        Optional<Oferta> oferta = ofertaRepository.findById(id);

        if (oferta.isPresent()) {
            return new ResponseEntity<>(oferta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/oferty")
    @PreAuthorize("hasRole('ROLE_PRACOWNIK')")
    public ResponseEntity<Oferta> createOferta(@RequestBody Oferta oferta) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Pracownikschroniska> pracownik = pracownikschroniskaRepository.findById(email);
        Pracownikschroniska pracownikschroniska = pracownik.get();//isPresent


        try {
            Oferta tempOferta = new Oferta(oferta.getTytul(), oferta.getOpis(), oferta.getImie(),oferta.getZdjecie());
            tempOferta.setIdPracownika(pracownikschroniska);
            Oferta ofertaToSave = ofertaRepository.save(tempOferta);
            return new ResponseEntity<>(ofertaToSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/nazwa-schroniska")
    @PreAuthorize("hasRole('ROLE_PRACOWNIK')")
    public ResponseEntity<Pracownikschroniska> updateSchroniskoName(@RequestBody Pracownikschroniska pracownikschroniska) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Pracownikschroniska> prac = pracownikschroniskaRepository.findById(email);

        if (prac.isPresent()) {
            Pracownikschroniska pracownik = prac.get();//isPresent
            pracownik.setNazwaSchroniska(pracownikschroniska.getNazwaSchroniska());
            return new ResponseEntity<>(pracownikschroniskaRepository.save(pracownik), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/oferty/filtered")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> getFilteredOferty(@RequestParam(required = false, name = "name", defaultValue = "") String name,
                                               @RequestParam(required = false, name = "title", defaultValue = "") String title,
                                               Pageable pageable) {

        try {
            List<Oferta> oferty = new ArrayList<>(ofertaService.getFilteredOfertaList(title, name));

            if (oferty.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > oferty.size() ? oferty.size() : (start + pageable.getPageSize());
            Page<Oferta> pages = new PageImpl<>(oferty.subList(start, end), pageable, oferty.size());

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

