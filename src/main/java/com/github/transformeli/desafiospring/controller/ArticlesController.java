package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {
    @Autowired
    private IProductService service;

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> createArticles(@RequestBody List<Product> articleList)
    {
        List<ProductDTO> productDtoList = new ArrayList<>();
        articleList.stream().forEach(System.out::println);
        return new ResponseEntity(productDtoList, HttpStatus.CREATED);
    }
}
