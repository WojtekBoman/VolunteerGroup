package ztw.bs5.PsiPatrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztw.bs5.PsiPatrol.Entities.News;
import ztw.bs5.PsiPatrol.Entities.Oferta;
import ztw.bs5.PsiPatrol.Repositories.NewsRepository;
import ztw.bs5.PsiPatrol.Repositories.OfertaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;


    @Override
    public List<News> getSortedNewses() {
        List<News> newsList = newsRepository.findAll();
//        Collections.sort(newsList, Comparator.comparing(p -> p.getDataDodania()));
        Collections.sort(newsList, new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                return o2.getDataDodania().compareTo(o1.getDataDodania());
            }
        });
        return newsList;
    }
}
