package ztw.bs5.PsiPatrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ztw.bs5.PsiPatrol.Entities.Uzytkownik;
import ztw.bs5.PsiPatrol.Entities.Wolontariusz;
import ztw.bs5.PsiPatrol.Entities.Wydarzenie;
import ztw.bs5.PsiPatrol.Services.WolontariuszService;
import ztw.bs5.PsiPatrol.Services.WolontariuszServiceImpl;
import ztw.bs5.PsiPatrol.Services.WydarzenieService;
import ztw.bs5.PsiPatrol.Services.WydarzenieServiceImpl;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
class ServeceTests {

    @Test
    void shouldReturnExpectedIsFullValue() {
        Wydarzenie testWydarzenie = new Wydarzenie();
        testWydarzenie.setLiczbaPotrzebnychWolontariuszy(10);
        testWydarzenie.setLiczbaPrzypisanychWolontariuszy(10);
        WydarzenieService wydarzenieService = new WydarzenieServiceImpl();

        //wydarzenie "pełne"
        Assertions.assertTrue(wydarzenieService.isFull(testWydarzenie));

        //wydarzenie "niepełne"
        testWydarzenie.setLiczbaPrzypisanychWolontariuszy(9);
        Assertions.assertFalse(wydarzenieService.isFull(testWydarzenie));

    }

    @Test
    void shouldReturnExpectedIsAssignedValue() {
        Wydarzenie testWydarzenie = new Wydarzenie("nazwa","miejsce","adres","opis",2,"Inne", Date.valueOf("2020-04-05"));
        testWydarzenie.setWolontariusze(new ArrayList<>());
        Wolontariusz testWolontariusz = new Wolontariusz("kuba@wp.pl","haslo","Imie","Nazwisko","Amator",0.1);
        WolontariuszService wolontariuszService = new WolontariuszServiceImpl();

        //wolontariusz nieprzypisany
        Assertions.assertFalse(wolontariuszService.isAssigned(testWolontariusz, testWydarzenie));

        //wolontariusz przypisany
        testWydarzenie.addWolontariusz(testWolontariusz);
        Assertions.assertTrue(wolontariuszService.isAssigned(testWolontariusz, testWydarzenie));


    }

}
