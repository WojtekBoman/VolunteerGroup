package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Zbiorka;

@Repository
public interface ZbiorkaRepository extends JpaRepository<Zbiorka, Integer> {


}
