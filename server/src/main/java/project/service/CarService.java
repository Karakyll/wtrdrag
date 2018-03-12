package project.service;

import project.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(final Car car);

    List<Car> findByPowerGreaterThan(int power);

    List<Car> findByMarkAndModel(String mark, String model);

    List<Car> getAllCars();

    Optional<Car> findCarById(Long carId);

    void deleteCarById(Long raceId);

}
