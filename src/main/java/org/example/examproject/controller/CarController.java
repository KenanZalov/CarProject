package org.example.examproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.examproject.dto.CarDto;
import org.example.examproject.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @GetMapping("/getAllCars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/addCar")
    public CarDto addCar(@RequestBody CarDto carDTO) {
        return carService.addCar(carDTO);
    }

    @PutMapping("/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDTO) {
        return carService.updateCar(id, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/year/{year}")
    public List<CarDto> getCarsByYear(@PathVariable int year) {
        return carService.getCarsByYear(year);
    }
}
