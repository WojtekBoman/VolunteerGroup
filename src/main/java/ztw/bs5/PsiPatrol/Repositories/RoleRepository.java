package ztw.bs5.PsiPatrol.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztw.bs5.PsiPatrol.Entities.ERole;
import ztw.bs5.PsiPatrol.Entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //integer/long
    Optional<Role> findByName(ERole name);
}
