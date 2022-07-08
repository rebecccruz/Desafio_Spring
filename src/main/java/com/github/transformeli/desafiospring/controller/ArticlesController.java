package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.enums.ParamOrderEnum;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

    @Autowired
    private IProductService service;

    @GetMapping("/articles")
    public ResponseEntity<List<ProductDTO>> getArticles(
            @RequestParam(required = false) Optional<String> category,
            @RequestParam(required = false) Optional<String> brand,
            @RequestParam(required = false) Optional<Boolean> freeShipping,
            @RequestParam(required = false) Optional<String> prestige,
            @RequestParam(required = false) Optional<Integer> order
    ) {
        return ResponseEntity.ok().body(service.getProductsByFilter(
                category,
                brand,
                freeShipping,
                prestige,
                order));
    }

    @GetMapping("/articles/category/{name}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String name) {
        List<ProductDTO> productsByCategory = service.getByCategory(name);
        return ResponseEntity.ok().body(productsByCategory);
    }

    @GetMapping("/articles/order/{orderId}")
    public ResponseEntity<List<Product>> getAllByOrder(@PathVariable Integer orderId) {
        List<Product> products = service.getAllByOrder(ParamOrderEnum.valueOf(orderId), service.getAllProducts());
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> createArticles(@RequestBody List<Product> articleList) {
        articleList.stream().forEach(product -> service.saveProduct(product));
        return new ResponseEntity(service.getAllArticles(articleList), HttpStatus.CREATED);
    }
}
