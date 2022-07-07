package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.enums.ParamOrderEnum;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ArticlesController {

    @Autowired
    private IProductService service;

    @GetMapping("/articles")
<<<<<<< HEAD
    public ResponseEntity<List<ProductDTO>> getAllArticles() {
        List<ProductDTO> productsDTO
                = service.getAllArticles();
        return ResponseEntity.ok().body(productsDTO);
    }

    @GetMapping("/articles/")
    public ResponseEntity<List<ProductDTO>> getByCategory(@RequestParam String category) {
=======
    public ResponseEntity<List<Product>> getAllArticles() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/articles/{category}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String category) {
>>>>>>> e36fc24 (atualizando)
        List<ProductDTO> productsByCategory = service.getByCategory(category);
        return ResponseEntity.ok().body(productsByCategory);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> createArticles(@RequestBody List<Product> articleList)
    {
        List<ProductDTO> productDtoList = new ArrayList<>();
        articleList.stream().forEach(System.out::println);
        return new ResponseEntity(productDtoList, HttpStatus.CREATED);
    }

    @GetMapping("/articles")
            public ResponseEntity<List<Product>> getAllByOrder(@RequestParam Integer order){
            List<Product> products = service.getAllByOrder(order);
            return ResponseEntity.ok().body(products);
        }
    }
