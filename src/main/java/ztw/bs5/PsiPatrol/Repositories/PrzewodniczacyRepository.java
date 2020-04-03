package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Przewodniczacy;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;

@Repository
public interface PrzewodniczacyRepository extends JpaRepository<Przewodniczacy, String> {







}
