package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Payload.Response.MessageResponse;
import ztw.bs5.PsiPatrol.Repositories.WolontariuszRepository;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;
import ztw.bs5.PsiPatrol.Services.WolontariuszService;
import ztw.bs5.PsiPatrol.Services.WydarzenieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UdzialController {


    @Autowired
    private WydarzenieService wydarzenieService;

    @Autowired
    private WolontariuszService wolontariuszService;

    @Autowired
    private WydarzenieRepository wydarzenieRepository;

    @Autowired
    private WolontariuszRepository wolontariuszRepository;


    @PostMapping("/udzial/wez/{id-wydarzenia}")
    @PreAuthorize("hasRole('WOLONTARIUSZ')")
    public ResponseEntity<?> assignWolontariuszToWydarzenie(@PathVariable("id-wydarzenia") int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Wolontariusz> wol = wolontariuszRepository.findById(email);
        Wolontariusz wolontariusz = wol.get();//isPresent

        Optional<Wydarzenie> wyd = wydarzenieRepository.findById(id);

        if (wyd.isPresent()) {
            Wydarzenie wydarzenieToSave = wyd.get();

            if (wolontariuszService.isAssigned(wolontariusz,wydarzenieToSave)){
                return new ResponseEntity<>(new MessageResponse("jest już przypisany"),HttpStatus.CONFLICT);
            }

            if (wydarzenieService.isFull(wydarzenieToSave)){
                return new ResponseEntity<>(new MessageResponse("jest full"),HttpStatus.CONFLICT);
            }

            wydarzenieToSave.addWolontariusz(wolontariusz);

            //zwiekszenie liczby przypisanych
            int participants = wydarzenieToSave.getLiczbaPrzypisanychWolontariuszy();
            participants++;
            if(participants==wydarzenieToSave.getLiczbaPotrzebnychWolontariuszy()){
                wydarzenieToSave.setCzyPelne(true);
            }

            wydarzenieToSave.setLiczbaPrzypisanychWolontariuszy(participants);
            wydarzenieRepository.save(wydarzenieToSave);
            wolontariuszService.updateWolonariszStats(wolontariusz);




            return new ResponseEntity<>(new MessageResponse("Przypisano pomyslnie"), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/udzial/anuluj/{id-wydarzenia}")
    @PreAuthorize("hasRole('WOLONTARIUSZ')")
    public ResponseEntity<?> cancelAssignmentWolontariuszToWydarzenie(@PathVariable("id-wydarzenia") int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Wolontariusz> wol = wolontariuszRepository.findById(email);
        Wolontariusz wolontariusz = wol.get();//isPresent

        Optional<Wydarzenie> wyd = wydarzenieRepository.findById(id);

        if (wyd.isPresent()) {
            Wydarzenie wydarzenieToSave = wyd.get();
            if (!wolontariuszService.isAssigned(wolontariusz,wydarzenieToSave)){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            wydarzenieToSave.removeWolontariusz(wolontariusz);

            //zmniejszenie liczby przypisanych wolontariuszy
            int participants = wydarzenieToSave.getLiczbaPrzypisanychWolontariuszy();
            participants--;

            wydarzenieToSave.setLiczbaPrzypisanychWolontariuszy(participants);
            wydarzenieToSave.setCzyPelne(false);

            wydarzenieRepository.save(wydarzenieToSave);
            wolontariuszService.updateWolonariszStats(wolontariusz);

            return new ResponseEntity<>(new MessageResponse("Anulowano pomyślnie"),HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/udzial/wydarzenia-uzytkownika")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wydarzenie>> getOwnWydarzenia() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Optional<Wolontariusz> wol = wolontariuszRepository.findById(email);
        Wolontariusz wolontariusz = wol.get();//isPresent

        List<Wydarzenie> wydarzenia = new ArrayList<>(wolontariusz.getWydarzenia());

        if (wydarzenia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(wydarzenia, HttpStatus.OK);

    }

    @GetMapping("/udzial/wydarzenia-uzytkownika/{id-uzytkownika}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wydarzenie>> getWydarzeniaUzytkownika(@PathVariable("id-uzytkownika") String email) {
        Optional<Wolontariusz> wol = wolontariuszRepository.findById(email);
        Wolontariusz wolontariusz = wol.get();//isPresent

        List<Wydarzenie> wydarzenia = new ArrayList<>(wolontariusz.getWydarzenia());

        if (wydarzenia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(wydarzenia, HttpStatus.OK);
    }

    @GetMapping("/udzial/uzytkownicy-wydarzenia/{id-wydarzenia}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wolontariusz>> getUzytkownicyWydarzenia(@PathVariable("id-wydarzenia") int id) {

        Optional<Wydarzenie> wydarzenie = wydarzenieRepository.findById(id);

        if (!wydarzenie.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Wolontariusz> listaWolontariuszy = new ArrayList<>(wydarzenie.get().getWolontariusze());

        if (listaWolontariuszy.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(listaWolontariuszy, HttpStatus.OK);

    }

}

