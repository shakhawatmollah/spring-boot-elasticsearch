package com.shakhawat.springbootelasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.shakhawat.springbootelasticsearch.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class ElasticSearchQuery {

    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchTemplate elasticsearchTemplate;

    private final String indexName = "products";

    public String createOrUpdateDocument(Product product) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(product.getId())
                .document(product)
        );
        if(response.result().name().equals("Created")){
            return "Document has been successfully created.";
        }else if(response.result().name().equals("Updated")){
            return "Document has been successfully updated.";
        }
        return "Error while performing the operation.";
    }

    public Product getDocumentById(String productId) throws IOException{
        Product product = null;
        GetResponse<Product> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                Product.class
        );
        if (response.found()) {
            product = response.source();
            assert product != null;
            System.out.println("Product name " + product.getName());
        } else {
            System.out.println ("Product not found");
        }

        return product;
    }

    public String deleteDocumentById(String productId) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));
        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return "Product with id " + deleteResponse.id() + " has been deleted.";
        }
        System.out.println("Product not found");
        return "Product with id " + deleteResponse.id() + " does not exist.";

    }

    public List<Product> searchAllDocuments() throws IOException {
        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse<Product> searchResponse =  elasticsearchClient.search(searchRequest, Product.class);
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for(Hit object : hits){
            System.out.println(object.source());
            products.add((Product) object.source());
        }
        return products;
    }

    public Boolean dropDocuments() throws IOException {
        return elasticsearchTemplate.indexOps(IndexCoordinates.of(indexName)).delete();
    }

}
