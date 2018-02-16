package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Car;
import project.repository.CarRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements project.service.CarService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findByPowerGreaterThan(int power) {
        return carRepository.findByPowerGreaterThan(power);
    }

    @Override
    public List<Car> findByMarkAndModel(String mark, String model) {
        return carRepository.findByMarkAndModel(mark, model);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
