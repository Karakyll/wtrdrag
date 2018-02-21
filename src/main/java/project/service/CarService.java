package project.service;

import project.entity.Car;

import java.util.List;

public interface CarService {

    Car save(final Car car);

    List<Car> findByPowerGreaterThan(int power);

    List<Car> findByMarkAndModel(String mark, String model);

    List<Car> getAllCars();

    Car findCarById(Long carId);

}
