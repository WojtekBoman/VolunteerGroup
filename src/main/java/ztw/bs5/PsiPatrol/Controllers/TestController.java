package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Services.UzytkownikService;
import ztw.bs5.PsiPatrol.Services.WydarzenieService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {


    @Autowired
    private UzytkownikService uzytkownikService;

    @Autowired
    private WydarzenieService wydarzenieService;


    @GetMapping("/uzytkownicy")
    public List<Uzytkownik> getAllUzytkownik() {
        return uzytkownikService.getAllUzytkownicy();
    }

    @GetMapping("/uzytkownicy/{id}")
    public Uzytkownik getUzytkownik(@PathVariable(value = "id") String email) {
        return uzytkownikService.getUzytkownik(email);
    }

    @GetMapping("/wydarzenia")
    public List<Wydarzenie> getAllWydarzenia() {
        return wydarzenieService.getAllWydarzenia();
    }
}
