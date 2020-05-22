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
import ztw.bs5.PsiPatrol.Entities.Pracownikschroniska;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;
import ztw.bs5.PsiPatrol.Repositories.PracownikschroniskaRepository;
import ztw.bs5.PsiPatrol.Repositories.ZbiorkaRepository;
import ztw.bs5.PsiPatrol.Services.ZbiorkaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ZbiorkaController {

    @Autowired
    private ZbiorkaRepository zbiorkaRepository;

    @Autowired
    private ZbiorkaService zbiorkaService;

    @Autowired
    private PracownikschroniskaRepository pracownikschroniskaRepository;

    @GetMapping("/zbiorki")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Zbiorka>> getAllZbiorki() {
        try {
            List<Zbiorka> zbiori = new ArrayList<>(zbiorkaRepository.findAll());

            if (zbiori.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(zbiori, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/zbiorki/{id}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<Zbiorka> getZbiorkaById(@PathVariable("id") int id) {
        Optional<Zbiorka> zbiorka = zbiorkaRepository.findById(id);

        if (zbiorka.isPresent()) {
            return new ResponseEntity<>(zbiorka.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/zbiorki")
    @PreAuthorize("hasRole('ROLE_PRACOWNIK')")
    public ResponseEntity<Zbiorka> createZbiorka(@RequestBody Zbiorka zbiorka) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Pracownikschroniska> pracownik = pracownikschroniskaRepository.findById(email);
        Pracownikschroniska pracownikschroniska = pracownik.get();//isPresent


        try {
            Zbiorka tempZbiorka = new Zbiorka(zbiorka.getTytul(),zbiorka.getOpis(),zbiorka.getDataRozpoczecia(),zbiorka.getDataZakonczenia(),zbiorka.getKwotaPotrzebna());
            tempZbiorka.setIdPracownika(pracownikschroniska);
            Zbiorka zbiorkaToSave = zbiorkaRepository.save(tempZbiorka);
            return new ResponseEntity<>(zbiorkaToSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/zbiorki/filtered")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> getFilteredOferty(@RequestParam(required = false, name = "title", defaultValue = "") String title,
//                                               @RequestParam(required = false, name = "description", defaultValue = "") String description,
//                                               @RequestParam(required = false, name = "beginDate", defaultValue = "") String endDate1,
//                                               @RequestParam(required = false, name = "endDate", defaultValue = "") String endDate2,
//                                               @RequestParam(required = false, name = "amountNeeded", defaultValue = "") double amountNeeded,
                                               Pageable pageable) {

        try {
            List<Zbiorka> zbiorki = new ArrayList<>(zbiorkaService.getFilteredZbiorkaList(title));

            if (zbiorki.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > zbiorki.size() ? zbiorki.size() : (start + pageable.getPageSize());
            Page<Zbiorka> pages = new PageImpl<>(zbiorki.subList(start, end), pageable, zbiorki.size());

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

