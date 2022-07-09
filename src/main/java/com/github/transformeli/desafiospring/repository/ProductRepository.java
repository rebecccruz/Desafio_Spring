package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.transformeli.desafiospring.exception.InternalServerException;
import com.github.transformeli.desafiospring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final String linkFile = "src/main/resources/products.json";

    /**
     * Get all products, this method call readFile
     * @author Larissa Navarro
     *
     */
    public List<Product> getAllProducts() {
        return readFile();
    }

    /**
     * Save clients, this method add the client in the list.
     * @author Isaias Finger and Larissa Navarro
     * @param product product object
     */
    public void saveProduct(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList, productListNew;
        try {
            productList = readFile();
            productListNew = new ArrayList<>(productList);
            productListNew.add(product);
            writer.writeValue(new File(linkFile), productListNew);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    /**
     * Update price and quantity product by product ID
     * @author: Lucas Pinheiro Rocha / Alexandre Borges Souza
     * @param product product to be updated
     */
    public List<Product> updateProduct(Product product) {
        List<Product> productsList = getAllProducts();
        List<Product> updatedProductList = productsList.stream().map(r-> {
            if (r.getProductId() == product.getProductId()) {
                return product;
            }
            return r;
        }).collect(Collectors.toList());
        this.updateProductsToFile(updatedProductList);
        return getAllProducts();
    }

    /**
     * Update products to JSON file
     * @author: Lucas Pinheiro Rocha / Alexandre Borges Souza
     * @param products List of products to be updated
     */
    private void updateProductsToFile(List<Product> products) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(linkFile), products);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    /**
     * This method read the file json.
     * @author Larissa Navarro
     */
    private List<Product> readFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = new ArrayList<>();
        try {
            list = Arrays.asList(mapper.readValue(new File(linkFile), Product[].class));
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
        return list;
    }

}
