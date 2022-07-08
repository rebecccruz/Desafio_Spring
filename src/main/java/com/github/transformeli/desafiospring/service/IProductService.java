package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.enums.ParamOrderEnum;
import com.github.transformeli.desafiospring.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();
    public List<ProductDTO> getAllArticles(List<Product> productList);
    public List<ProductDTO> getByCategory(String category);
    public void saveProduct(Product product);
    public List<Product> getAllByOrder(ParamOrderEnum orderBy, List<Product> productList);
    public List<ProductDTO> getProductsByFilter(
            Optional<String> category,
            Optional<String> brand,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            Optional<Integer> order);
    public List<Product> updateStockPriceArticle(Product product);
}
