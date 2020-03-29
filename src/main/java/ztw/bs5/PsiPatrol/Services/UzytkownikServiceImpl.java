package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;

import java.util.List;

@Service
public class UzytkownikServiceImpl implements UzytkownikService {


    @Autowired
    UzytkownikRepository uzytkownikRopository;

    @Override
    public List<Uzytkownik> getAllUzytkownicy() {
        return uzytkownikRopository.findAll();
    }

    @Override
    public Uzytkownik getUzytkownik(String email) {
        return uzytkownikRopository.findUzytkownikByEmail(email);
    }
}
