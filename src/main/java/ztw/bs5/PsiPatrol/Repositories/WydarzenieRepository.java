package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WydarzenieRepository extends JpaRepository<Wydarzenie, Integer> {
    List<Wydarzenie> findAllByDataRozpoczeciaGreaterThanEqualAndDataRozpoczeciaLessThanEqual(Date start, Date end);



}
