package ztw.bs5.PsiPatrol.Services;


import ztw.bs5.PsiPatrol.Entities.News;

import java.util.List;

public interface NewsService {
    List<News> getSortedNewses();
}
