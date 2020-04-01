package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/wol")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public String userAccess() {
        return "Wol Content.";
    }

    @GetMapping("/pra")
    @PreAuthorize("hasRole('PRACOWNIK')")
    public String moderatorAccess() {
        return "Pracownik Board.";
    }

    @GetMapping("/prz")
    @PreAuthorize("hasRole('ROLE_PRZEWODNICZACY')")
    public String adminAccess() {
        return "Przewodniczacy Board.";
    }
}
