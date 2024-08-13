package com.shakhawat.springbootelasticsearch.service.impl;

import com.shakhawat.springbootelasticsearch.model.Car;
import com.shakhawat.springbootelasticsearch.repository.CarRepository;
import com.shakhawat.springbootelasticsearch.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){
            carRepository.deleteById(id);
        }
    }

    @Override
    public List<Car> findAllByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    @Override
    public List<Car> findByModel(String modelName) {
        return carRepository.findByModelIgnoreCaseContaining(modelName);
    }

    @Override
    public List<Car> findByPriceBetween(double low, double high) {
        return carRepository.findByPriceBetween(low, high);
    }

}
