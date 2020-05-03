package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {


}
