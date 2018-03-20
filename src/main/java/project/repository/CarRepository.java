package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from cars where power>?1", nativeQuery = true)
    List<Car> findByPowerGreaterThan(int power);

    List<Car> findByMarkAndModel(String mark, String model);

    List<Car> findAll();

}
