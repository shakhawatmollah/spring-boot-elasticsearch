package com.shakhawat.springbootelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.shakhawat.springbootelasticsearch.model.Product;
import com.shakhawat.springbootelasticsearch.service.ElasticSearchService;
import com.shakhawat.springbootelasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    Iterable<Product> findAll() {
        return productService.getProducts();

    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        System.out.println(searchResponse.hits().hits().toString());
        return searchResponse.hits().hits().toString();
    }

    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductsServices();

        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @GetMapping("/matchAllProducts/{fieldValue}")
    public List<Product> matchAllProductsWithName(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchProductsWithName(fieldValue);

        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @GetMapping("/autoSuggest/{partialProductName}")
    List<String> autoSuggestProductSearch(@PathVariable String partialProductName) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.autoSuggestProduct(partialProductName);
        List<Hit<Product>> hitList = searchResponse.hits().hits();
        List<Product> productList = new ArrayList<>();
        for (Hit<Product> hit : hitList) {
            productList.add(hit.source());
        }
        List<String> listOfProductNames = new ArrayList<>();
        for (Product product : productList) {
            listOfProductNames.add(product.getName());
        }
        return listOfProductNames;
    }

    @GetMapping("/boolQuery/{productName}/{qty}")
    public List<Product> boolQuery(@PathVariable String productName, @PathVariable Integer qty) throws IOException {

        SearchResponse<Product> searchResponse = elasticSearchService.boolQueryImpl(productName, qty);

        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

}
