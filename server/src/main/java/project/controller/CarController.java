package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.entity.Car;
import project.exceptions.CarNotFoundException;
import project.service.CarService;
import project.service.SponsorService;

import java.net.URI;
import java.util.List;

/**
 * Rest controller class for "/cars/**" requests
 */
@RestController
@RequestMapping(value = "/cars")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    SponsorService sponsorService;

    private static final Logger logger =
            LoggerFactory.getLogger(CarController.class);

    /**
     * Map all "/cars" GET requests
     * @return - JSON with all cars
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Car> getAllCars() {
        return this.carService.getAllCars();
    }

    /**
     * Map all "/cars/{id}" GET requests
     * @param carId - car identity
     * @return - JSON car with {id}
     */
    @RequestMapping(value = "/{carId}", method = RequestMethod.GET)
    Car getCar(@PathVariable Long carId) {
        logger.info("get car id=[" + carId + "].");
        return carService.findCarById(carId).orElseThrow(() -> new CarNotFoundException(carId));
    }

    /**
     * Map all "/cars" POST requests. Save new Car in DataBase
     * @param inputCar - Take JSON Car in Body
     * @return - status "created" with link to created resource
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addCar(@RequestBody Car inputCar) {
            Car result = carService.save(
                    new Car.CarBuilder(inputCar.getMark(), inputCar.getModel())
                            .pilotFirstName(inputCar.getPilotFirstName())
                            .pilotLastName(inputCar.getPilotLastName())
                            .power(inputCar.getPower())
                            .torque(inputCar.getTorque())
                            .spec(inputCar.getSpec())
                            .sponsor(inputCar.getSponsor())
                            .build());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{carId}")
                    .buildAndExpand(result.getCarId()).toUri();
            return ResponseEntity.created(location).body(result);
    }

    /**
     * Map all "/cars/{id}" DELETE requests
     * @param carId - car identity
     * @return - response status
     */
    @RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCar(@PathVariable Long carId){
        if (carService.findCarById(carId).isPresent()) {
            carService.deleteCarById(carId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
