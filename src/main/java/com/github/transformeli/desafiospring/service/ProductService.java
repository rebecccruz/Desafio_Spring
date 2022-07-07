package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    @Override
    public Product getByCategory(String category) {
        return repo.getByCategory(category);
    }

    @Override
    public void saveProduct(Product product) {
        repo.saveProduct(product);
    }

    @Override
    public List<Product> getAllByOrder(Integer order) {
        List<Product> productList = getAllProducts();
        List<Product> result = null;
        switch (order) {
            case 0: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case 1: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case 2: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
            case 3: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
        }
        return result;
    }



//    @Override
//    public List<Product> getAllHigherPrice() {
//        List<Product> productList = repo.getAllProducts();
//        return productList.stream()
//                .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Product> getAllLowerPrice() {
//        List<Product> productList = repo.getAllProducts();
//        return productList.stream()
//                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Product> getAlphaAsc() {
//        List<Product> productList = repo.getAllProducts();
//        return productList.stream()
//                .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Product> getAlphaDesc() {
//        List<Product> productList = repo.getAllProducts();
//        return productList.stream()
//                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
//                .collect(Collectors.toList());
//    }



}
