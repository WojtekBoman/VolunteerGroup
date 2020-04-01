package ztw.bs5.PsiPatrol.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Repositories.UzytkownikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UzytkownikRepository userRepository;

    @Override
    @Transactional//dobry?
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Uzytkownik user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

}
