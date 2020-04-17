package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private WydarzenieRepository wydarzenieRepository;


    @GetMapping("/wydarzenia")
    public ResponseEntity<List<Wydarzenie>> getAllWydarzenia() {
        try {
            List<Wydarzenie> wydarzenia = new ArrayList<>();
            wydarzenieRepository.findAll().forEach(wydarzenia::add);

            if (wydarzenia.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wydarzenia, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/wol")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public String userAccess() {
        return "Wol Content.";
    }

    @GetMapping("/pra")
    @PreAuthorize("hasRole('PRACOWNIK')")
    public String moderatorAccess() {
        return "Pracownik Board.";
    }

    @GetMapping("/prz")
    @PreAuthorize("hasRole('ROLE_PRZEWODNICZACY')")
    public String adminAccess() {
        return "Przewodniczacy Board.";
    }
}
