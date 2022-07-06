package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.service.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {
    @Autowired
    private IArticlesService service;
}
