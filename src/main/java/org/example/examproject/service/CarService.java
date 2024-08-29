package org.example.examproject.service;

import lombok.RequiredArgsConstructor;
import org.example.examproject.dto.CarDto;
import org.example.examproject.model.Brand;
import org.example.examproject.model.Car;
import org.example.examproject.repository.BrandRepository;
import org.example.examproject.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private  final BrandRepository brandRepository;


    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDto> carDtos = new ArrayList<>();
        cars.forEach(car -> carDtos.add(modelMapper.map(car, CarDto.class)));
        return carDtos;
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        return modelMapper.map(car, CarDto.class);
    }

    public CarDto addCar(CarDto carDTO) {
        Brand brand = brandRepository.findByName(carDTO.getBrandName());
        if (brand == null) {
            throw new RuntimeException("Brand not found");
        }
        Car car = modelMapper.map(carDTO, Car.class);
        car.setBrand(brand);
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }


    public CarDto updateCar(Long id, CarDto carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        Brand brand = brandRepository.findByName(carDTO.getBrandName());
        if (brand == null) {
            throw new RuntimeException("Brand not found");
        }
        car.setCarModel(carDTO.getModel());
        car.setProdYear(carDTO.getYear());
        car.setBrand(brand);
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
    }

    public List<CarDto> getCarsByYear(int prodYear) {
        return carRepository.findByProdYear(prodYear)
                .stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }
}
