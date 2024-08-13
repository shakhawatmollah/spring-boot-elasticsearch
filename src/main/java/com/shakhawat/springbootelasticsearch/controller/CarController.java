package com.shakhawat.springbootelasticsearch.controller;

import com.shakhawat.springbootelasticsearch.model.Car;
import com.shakhawat.springbootelasticsearch.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable String id) {
        return carService.findById(id).orElse(null);
    }

    @GetMapping
    public Iterable<Car> findAll() {
        return carService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        carService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody Car car) {
        carService.save(car);
    }

    @GetMapping("/find-by-brand")
    public List<Car> findByBrand(@RequestParam String brand) {
        return carService.findAllByBrand(brand);
    }

    @GetMapping("/find-by-model")
    public List<Car> findByModel(@RequestParam String model) {
        return carService.findByModel(model);
    }

    @GetMapping("/prices/{low}/{high}")
    public List<Car> getItemsByPriceRange(@PathVariable("low") double low, @PathVariable("high") double high) {
        return carService.findByPriceBetween(low, high);
    }

}
