package dat3.cars.repositories;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            Car car1 = new Car("brand1", "model1", 10, 50);
            Car car2 = new Car("brand2", "model2", 20, 60);
            Car car3 = new Car("brand3", "model3", 30, 70);
            carRepository.saveAll(List.of(car1, car2, car3));
        }
    }

    @Test
    public void testAll(){
        double count = carRepository.count();
        assertEquals(3, count);
    }

    @Test
    public void testFindCarsByBrandLike(){
        List<Car> cars = carRepository.findCarsByBrandLike("%brand%");
        assertEquals(3, cars.size());
    }

    @Test
    public void testDeleteAll(){
        carRepository.deleteAll();
        assertEquals(0, carRepository.count());
    }

    @Test
    void testFindAllCarsBestDiscount(){
        Car bestDisc1 = new Car("bestDisc1", "model", 1000, 100);
        Car bestDisc2 = new Car("bestDisc2", "model", 69420, 100);
        Car bestDisc3 = new Car("bestDisc3", "model", 1020, 100);
        Car bestDisc4 = new Car("bestDisc4", "model", 1234, 100);
        carRepository.saveAll(List.of(bestDisc1, bestDisc2, bestDisc3, bestDisc4));

        List<Car> cars = carRepository.findCarsByBestDiscount();

        assertEquals(4, cars.size());
    }

}