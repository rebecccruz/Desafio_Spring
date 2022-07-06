package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.Articles;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IArticlesService {

    public List<Articles> getAllArticles();
    public Articles getByCategory(String category);
    public void saveArticles(Articles articles);
    public List<Articles> getAllAsc();
    public List<Articles> getAllDesc();
    public List<Articles> getAllHigherPrice();
    public List<Articles> getAllLowerPrice();

}
