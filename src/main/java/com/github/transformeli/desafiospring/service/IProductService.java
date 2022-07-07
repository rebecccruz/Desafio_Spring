package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();
    public Product getByCategory(String category);
    public void saveProduct(Product product);
//    public List<Product> getAllHigherPrice();
//    public List<Product> getAllLowerPrice();
//    public List<Product> getAlphaAsc();
//    public List<Product> getAlphaDesc();
    public List<Product> getAllByOrder(Integer order);


}
