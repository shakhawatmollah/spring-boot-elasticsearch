package com.shakhawat.springbootelasticsearch.repository;

import com.shakhawat.springbootelasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

}
