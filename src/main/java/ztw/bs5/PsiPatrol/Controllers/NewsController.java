package ztw.bs5.PsiPatrol.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ztw.bs5.PsiPatrol.Entities.News;
import ztw.bs5.PsiPatrol.Entities.Przewodniczacy;
import ztw.bs5.PsiPatrol.Repositories.NewsRepository;
import ztw.bs5.PsiPatrol.Repositories.PrzewodniczacyRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PrzewodniczacyRepository przewodniczacyRepository;

    @GetMapping("/newsy")
    public ResponseEntity<List<News>> getAllNewsy() {
        try {
            List<News> newsList = new ArrayList<>(newsRepository.findAll());

            if (newsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(newsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/newsy/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") int id) {
        Optional<News> newsOptional = newsRepository.findById(id);

        if (newsOptional.isPresent()) {
            return new ResponseEntity<>(newsOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/newsy")
    @PreAuthorize("hasRole('WOLONTARIUSZ') or hasRole('PRACOWNIK') or hasRole('PRZEWODNICZACY')")
    public ResponseEntity<News> createNews(@RequestBody News news) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<Przewodniczacy> przewodniczacyOptional = przewodniczacyRepository.findById(email);
        Przewodniczacy przewodniczacy = przewodniczacyOptional.get();//isPresent


        try {
            News tempNews = new News(news.getNaglowek(),news.getTresc(), LocalDate.now());
            tempNews.setIdAutora(przewodniczacy);
            News newsToSave = newsRepository.save(tempNews);
            return new ResponseEntity<>(newsToSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/newsy/filtered")
    public ResponseEntity<?> getFilteredNewsy(Pageable pageable) {

        try {
            List<News> newsList = new ArrayList<>(newsRepository.findAll());

            if (newsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > newsList.size() ? newsList.size() : (start + pageable.getPageSize());
            Page<News> pages = new PageImpl<>(newsList.subList(start, end), pageable, newsList.size());

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

