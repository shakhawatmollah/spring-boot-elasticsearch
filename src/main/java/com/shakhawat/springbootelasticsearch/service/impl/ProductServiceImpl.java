package com.shakhawat.springbootelasticsearch.service.impl;

import com.shakhawat.springbootelasticsearch.model.Product;
import com.shakhawat.springbootelasticsearch.repository.ProductRepository;
import com.shakhawat.springbootelasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, String id) {
        Optional<Product> prod  = productRepository.findById(id);
        if(prod.isPresent()){
            Product productObj = prod.get();
            productObj.setQuantity(product.getQuantity());
            productObj.setDescription(product.getDescription());
            productRepository.save(productObj);
            return productObj;
        }
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> productObj  = productRepository.findById(id);
        if(productObj.isPresent()){
            productRepository.deleteById(id);
        }
    }
}
