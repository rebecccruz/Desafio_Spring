package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.enums.ParamOrderEnum;
import com.github.transformeli.desafiospring.exception.BadRequestException;
import com.github.transformeli.desafiospring.exception.InternalServerException;
import com.github.transformeli.desafiospring.exception.PreConditionFailedException;
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
            @RequestParam(required = false) Optional<String> freeShipping,
            @RequestParam(required = false) Optional<String> prestige,
            @RequestParam(required = false) Optional<String> order
    ) {
        try {
            Integer.valueOf(order.get());
        } catch (Exception ex) {
            throw new PreConditionFailedException(ex.getMessage());
        }
        Optional<Boolean> freeShippingId = Optional.empty();
        if(freeShipping.isPresent())
        {
            freeShippingId = Optional.of(Boolean.valueOf(freeShipping.get()));
        }
        return ResponseEntity.ok().body(service.getProductsByFilter(
                category,
                brand,
                freeShippingId,
                prestige,
                Optional.of(Integer.valueOf(order.get()))));
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> createArticles(@RequestBody List<Product> articleList) {
        articleList.stream().forEach(product -> service.saveProduct(product));
        return new ResponseEntity(service.getAllArticles(articleList), HttpStatus.CREATED);
    }

    @PutMapping("/articles/update-articles/{productID}")
    public ResponseEntity<List<Product>> updateStockPriceArticle(@PathVariable Long productID, @RequestBody Product product) {
        product.setProductId(productID);
        List<Product> result = service.updateStockPriceArticle(product);
        return ResponseEntity.ok().body(result);
    }
}
