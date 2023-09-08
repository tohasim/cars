package dat3.cars.repositories;

import dat3.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarsByBrandLike(String name);

    @Query(
            value = "SELECT * FROM car WHERE max_discount = (SELECT max_discount FROM car ORDER BY max_discount DESC LIMIT 1)",
            nativeQuery = true
    )
    List<Car> findCarsByBestDiscount();
}
