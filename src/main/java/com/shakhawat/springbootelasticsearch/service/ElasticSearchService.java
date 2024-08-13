package com.shakhawat.springbootelasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.shakhawat.springbootelasticsearch.model.Product;
import com.shakhawat.springbootelasticsearch.util.ElasticSearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> matchAllProductsServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> matchProductsWithName(String fieldValue) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierWithNameField(fieldValue);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> autoSuggestProduct(String partialProductName) throws IOException {

        Supplier<Query> supplier = ElasticSearchUtil.createSupplierAutoSuggest(partialProductName);
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println(" elasticsearch auto suggestion query" + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> boolQueryImpl(String productName, Integer qty) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierQueryForBoolQuery(productName, qty);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

}
