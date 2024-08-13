package com.shakhawat.springbootelasticsearch.service;

import com.shakhawat.springbootelasticsearch.model.Product;

public interface ProductService {

    Iterable<Product> getProducts();

    Product saveProduct(Product product);

    Product updateProduct(Product product, String id);

    void deleteProduct(String id);

}
