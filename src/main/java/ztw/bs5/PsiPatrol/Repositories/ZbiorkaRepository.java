package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ZbiorkaRepository extends JpaRepository<Zbiorka, Integer> {

    List<Zbiorka> findAllByDataRozpoczeciaGreaterThanEqualAndDataRozpoczeciaIsLessThanEqual(LocalDate start, LocalDate end);

}
