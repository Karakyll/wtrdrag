package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Car;
import project.exceptions.CarNotFoundException;
import project.repository.CarRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Class for car service operations
 */
@Service("carService")
public class CarServiceImpl implements project.service.CarService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CarRepository carRepository;

    /**
     * Save car to DataBase
     * @param car - car exemplar
     * @return - car
     */
    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    /**
     * Find all cars with power greater than power
     * @param power - power
     * @return - list of cars
     */
    @Override
    public List<Car> findByPowerGreaterThan(int power) {
        return carRepository.findByPowerGreaterThan(power);
    }

    /**
     * Find all cars with mark and model
     * @param mark - mark
     * @param model - model
     * @return - list of cars
     */
    @Override
    public List<Car> findByMarkAndModel(String mark, String model) {
        return carRepository.findByMarkAndModel(mark, model);
    }

    /**
     * Find all cars in database
     * @return - list of cars
     */
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Find car with ID in dataBase
     * @param carId - car identity
     * @return - car
     */
    @Override
    public Car findCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
    }
}
