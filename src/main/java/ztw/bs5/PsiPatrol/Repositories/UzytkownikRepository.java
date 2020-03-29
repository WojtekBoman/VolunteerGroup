package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;

import javax.transaction.Transactional;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik, String> {

public Uzytkownik findUzytkownikByEmail(String email);


}
