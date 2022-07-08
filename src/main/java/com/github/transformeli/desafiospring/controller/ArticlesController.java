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
    public ResponseEntity<List<ProductDTO>> getAllArticles(@RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            List<ProductDTO> productsDTO = service.getAllArticles(service.getAllProducts());
            return ResponseEntity.ok().body(productsDTO);
        }
        List<Product> filtredProducts = service.getAllFromFilters(params);
        List<Product> orderedProducts = service.getAllByOrder(
                Integer.valueOf(params.getOrDefault("order", "0")), filtredProducts);
        return ResponseEntity.ok().body(service.getAllArticles(orderedProducts));
    }

    @GetMapping("/articles/category/{name}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String name) {
        List<ProductDTO> productsByCategory = service.getByCategory(name);
        return ResponseEntity.ok().body(productsByCategory);
    }

    @GetMapping("/articles/order/{orderId}")
    public ResponseEntity<List<Product>> getAllByOrder(@PathVariable Integer orderId) {
        List<Product> products = service.getAllByOrder(orderId, service.getAllProducts());
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> createArticles(@RequestBody List<Product> articleList) {
        articleList.stream().forEach(product -> service.saveProduct(product));
        return new ResponseEntity(service.getAllArticles(articleList), HttpStatus.CREATED);
    }
}
