package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wiadomosc;

import java.util.List;

@Repository
public interface WiadomoscRepository extends JpaRepository<Wiadomosc, Integer> {
    List<Wiadomosc> findByEmailAdresata(Uzytkownik uzytkownik);
    List<Wiadomosc> findByEmailNadawcy(Uzytkownik uzytkownik);

}
