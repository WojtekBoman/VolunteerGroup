package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;
import ztw.bs5.PsiPatrol.Repositories.WydarzenieRepository;
import ztw.bs5.PsiPatrol.Services.UzytkownikService;
import ztw.bs5.PsiPatrol.Services.UzytkownikServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {


//    @Autowired
//    private UzytkownikService uzytkownikService;

    @Autowired
    private WydarzenieRepository wydarzenieRepository;

    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    @GetMapping("/uzytkownicy")
    public List<Uzytkownik> getAllUzytkownik() {
        return uzytkownikRepository.findAll();
    }

    @GetMapping("/uzytkownicy/{id}")
    public Uzytkownik getJakiesUzytkownik(@PathVariable(value = "id") String email) {
        return uzytkownikRepository.findUzytkownikByEmail(email);
    }

    @GetMapping("/wydarzenia")
    public List<Wydarzenie> getAllWydarzenia() {
        return wydarzenieRepository.findAll();
    }
}
