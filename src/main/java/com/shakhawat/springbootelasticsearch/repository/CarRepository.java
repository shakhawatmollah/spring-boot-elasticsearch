package com.shakhawat.springbootelasticsearch.repository;

import com.shakhawat.springbootelasticsearch.model.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CarRepository extends ElasticsearchRepository<Car, String> {

    List<Car> findAllByBrand(String brand);

    List<Car> findByModelIgnoreCaseContaining(String modelName);

    List<Car> findByPriceBetween(double low, double high);
}
