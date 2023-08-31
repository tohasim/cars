package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
public class CarServiceTest {
    @Autowired
    CarRepository carRepository;

    CarService carService;

    Car c1, c2, c3;

    @BeforeEach
    void setUp(){
        c1 = carRepository.save(new Car("brand1", "model1", 10, 1));
        c2 = carRepository.save(new Car("brand2", "model2", 20, 2));
        c3 = carRepository.save(new Car("brand3", "model3", 30, 3));
        carService = new CarService(carRepository);
    }

    @Test
    void testGetCarsAllDetails(){
        List<CarResponse> carResponseList = carService.getCars(true);
        assertEquals(3, carResponseList.size());
        assertNotNull(carResponseList.get(0).getBestDiscount());
    }

    @Test
    void testGetCarsNoDetails() {
        List<CarResponse> carResponseList = carService.getCars(false);
        assertEquals(3, carResponseList.size());
        assertNull(carResponseList.get(0).getBestDiscount());
    }

    @Test
    void testFindByIdFound(){
        CarResponse response = carService.findById(1);
        assertEquals("model1", response.getModel());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()->carService.findById(10));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

    }

    @Test
    void testAddCar() {
        CarRequest request = CarRequest.builder().
                brand("addedBrand").
                model("addedModel").
                pricePrDay(200).
                bestDiscount(100).
                build();
        CarResponse response = carService.addCar(request);
        assertEquals("addedBrand", response.getBrand());
        assertTrue(carRepository.existsById(4));
    }

    @Test
    void testEditCar() {
        LocalDateTime originalEdited = carService.findById(1).getEdited();
        CarRequest request = new CarRequest(c1);
        request.setBrand("changedBrand");
        carService.editCar(request, 1);
        carRepository.flush();
        CarResponse response = carService.findById(1);
        assertEquals("changedBrand", response.getBrand());
        assertEquals("model1", response.getModel());
        assertEquals(10, response.getPricePrDay());
        assertEquals(1, response.getBestDiscount());
        assertTrue(originalEdited.isBefore(response.getEdited()));
    }

    @Test
    void testEditCarNotFound() {
        CarRequest request = new CarRequest();
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> carService.editCar(request, 420)
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testSetBestDiscountForCar() {
        carService.setBestDiscount(1, 10);
        CarResponse response = carService.findById(1);
        assertEquals(10, response.getBestDiscount());
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCar(1);
        assertFalse(carRepository.existsById(1));
    }

    @Test
    void testDeleteCar_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> carService.deleteCar(10));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
