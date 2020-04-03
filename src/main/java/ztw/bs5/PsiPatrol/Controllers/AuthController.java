package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.*;
import ztw.bs5.PsiPatrol.Payload.Request.LoginRequest;
import ztw.bs5.PsiPatrol.Payload.Request.SignupRequest;
import ztw.bs5.PsiPatrol.Payload.Response.JwtResponse;
import ztw.bs5.PsiPatrol.Payload.Response.MessageResponse;

import ztw.bs5.PsiPatrol.Repositories.*;
import ztw.bs5.PsiPatrol.Security.Jwt.JwtUtils;
import ztw.bs5.PsiPatrol.Security.Services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UzytkownikRepository userRepository;

    @Autowired
    WolontariuszRepository wolontariuszRepository;

    @Autowired
    PrzewodniczacyRepository przewodniczacyRepository;

    @Autowired
    PracownikschroniskaRepository pracownikschroniskaRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getHaslo()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getImie(),
                userDetails.getNazwisko(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }


        // Create new user's account
        Uzytkownik user = new Uzytkownik(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getHaslo()),
                signUpRequest.getImie(),
                signUpRequest.getNazwisko());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_WOLONTARIUSZ)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
           // user.setRoles(roles);
            Wolontariusz wolontariusz = new Wolontariusz(user.getEmail(), user.getHaslo(),
                    user.getImie(), user.getNazwisko(), "Amator",0 );
            wolontariusz.setRoles(roles);
            wolontariuszRepository.save(wolontariusz);
//            wolontariuszRepository.save(new Wolontariusz(user.getEmail(), user.getHaslo(),
//                    user.getImie(),
//                    user.getNazwisko(), "Amator",0));//testowanie
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "prz":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_PRZEWODNICZACY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
//                        user.setRoles(roles);
                        Przewodniczacy przewodniczacy = new Przewodniczacy(user.getEmail(), user.getHaslo(),
                                user.getImie(), user.getNazwisko(), 0);
                        przewodniczacy.setRoles(roles);
                        przewodniczacyRepository.save(przewodniczacy);
//                        przewodniczacyRepository.save(new Przewodniczacy(user.getEmail(), user.getHaslo(),
//                                user.getImie(),
//                                user.getNazwisko(), 0));//testowanie

                        break;
                    case "pra":
                        Role modRole = roleRepository.findByName(ERole.ROLE_PRACOWNIK)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

//                        user.setRoles(roles);
                        Pracownikschroniska pracownikschroniska = new Pracownikschroniska(user.getEmail(), user.getHaslo(),
                                user.getImie(), user.getNazwisko(), "Brak nazwy");
                        pracownikschroniska.setRoles(roles);
                        pracownikschroniskaRepository.save(pracownikschroniska);
//                        pracownikschroniskaRepository.save(new Pracownikschroniska(user.getEmail(), user.getHaslo(),
//                                user.getImie(),
//                                user.getNazwisko(), "Brak nazwy"));//testowanie
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_WOLONTARIUSZ)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        Wolontariusz wolontariusz = new Wolontariusz(user.getEmail(), user.getHaslo(),
                                user.getImie(), user.getNazwisko(), "Amator",0 );
                        wolontariusz.setRoles(roles);
                        wolontariuszRepository.save(wolontariusz);
                }
            });
        }

//        user.setRoles(roles);
       // userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}