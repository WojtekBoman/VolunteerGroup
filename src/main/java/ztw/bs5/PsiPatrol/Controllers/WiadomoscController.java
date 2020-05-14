package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wiadomosc;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WiadomoscRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class WiadomoscController {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;
    @Autowired
    private WiadomoscRepository wiadomoscRepository;

    @GetMapping("/wiadomosci/odebrane")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wiadomosc>> getWiadomosciOdebrane() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailOdbiorca = auth.getName();
        Optional<Uzytkownik> uzytkOdbiorca = uzytkownikRepository.findByEmail(emailOdbiorca);
        Uzytkownik uzytkownikOdbiorca = uzytkOdbiorca.get();//isPresent

        try {
            List<Wiadomosc> wiadomosci = new ArrayList<>(wiadomoscRepository.findByEmailAdresata(uzytkownikOdbiorca));

            if (wiadomosci.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wiadomosci, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wiadomosci/wyslane")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wiadomosc>> getWiadomosciWyslane() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailNadawca = auth.getName();
        Optional<Uzytkownik> uzytkNadawca = uzytkownikRepository.findByEmail(emailNadawca);
        Uzytkownik uzytkownikNadawca = uzytkNadawca.get();//isPresent

        try {
            List<Wiadomosc> wiadomosci = new ArrayList<>(wiadomoscRepository.findByEmailNadawcy(uzytkownikNadawca));

            if (wiadomosci.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wiadomosci, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wiadomosci/{id}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<Wiadomosc> getWiadomoscById(@PathVariable("id") int id) {
        Optional<Wiadomosc> wiadomosc = wiadomoscRepository.findById(id);

        if (wiadomosc.isPresent()) {
            return new ResponseEntity<>(wiadomosc.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/wiadomosci")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<List<Wiadomosc>> getAllWiadomosci() {
        try {
            List<Wiadomosc> wiadomosci = new ArrayList<>(wiadomoscRepository.findAll());

            if (wiadomosci.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wiadomosci, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/wiadomosci/wyslij/{email}")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<?> sendWiadomosc(@RequestBody Wiadomosc wiadomosc, @PathVariable("email") String emailAdresat){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailNadawca = auth.getName();
        Optional<Uzytkownik> uzytkNadawca = uzytkownikRepository.findByEmail(emailNadawca);
        Uzytkownik uzytkownikNadawca = uzytkNadawca.get();//isPresent

        Optional<Uzytkownik> uzytkAdresat = uzytkownikRepository.findByEmail(emailAdresat);
        Uzytkownik uzytkownikAdresat = uzytkAdresat.get();//isPresent


        try {
            Wiadomosc tempWiad = new Wiadomosc(uzytkownikAdresat, uzytkownikNadawca, wiadomosc.getTemat(),wiadomosc.getTresc());
           wiadomoscRepository.save(tempWiad);

            return new ResponseEntity<>("Wysłano pomyślnie!", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}

