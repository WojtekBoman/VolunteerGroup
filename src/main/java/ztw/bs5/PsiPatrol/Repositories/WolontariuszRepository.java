package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;

import java.util.List;
import java.util.Optional;

@Repository
public interface WolontariuszRepository extends JpaRepository<Wolontariusz, String> {


List<Wolontariusz> findAllByOrderByProcentowaAktywnoscDesc();




}
