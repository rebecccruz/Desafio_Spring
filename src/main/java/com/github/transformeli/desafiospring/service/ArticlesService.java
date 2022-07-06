package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.Articles;
import com.github.transformeli.desafiospring.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class ArticlesService implements IArticlesService {

    @Autowired
    private ArticlesRepository repo;

    @Override
    public List<Articles> getAllArticles() {
        return repo.getAllArticles();
    }

    @Override
    public Articles getByCategory(String category) {
         return repo.getByCategory(category);
    }

    @Override
    public void saveArticles(Articles articles) {
        repo.saveArticles(articles);
    }

    @Override
    public List<Articles> getAllAsc() {
       // repo.getAllArticles().stream().sorted((x,y) -> x.getName().length() - y.getName().length()).;
        return null;
    }

    @Override
    public List<Articles> getAllDesc() {
        return null;
    }

    @Override
    public List<Articles> getAllHigherPrice() {
        return null;
    }

    @Override
    public List<Articles> getAllLowerPrice() {
        return null;
    }


}
