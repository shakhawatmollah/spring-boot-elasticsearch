package com.shakhawat.springbootelasticsearch.service;

import com.shakhawat.springbootelasticsearch.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAllByBrand(String brand);

    List<Car> findByModel(String modelName);

    List<Car> findByPriceBetween(double low, double high);

    Car save(Car car);

    Optional<Car> findById(String id);

    Iterable<Car> findAll();

    void deleteById(String id);
}
