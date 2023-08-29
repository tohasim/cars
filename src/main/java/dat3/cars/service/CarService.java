package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> carList = carRepository.findAll();
        List<CarResponse> carResponseList = new ArrayList<>();
        for (Car car : carList) {
            carResponseList.add(new CarResponse(car, includeAll));
        }
        return carResponseList;
    }

    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));
        return new CarResponse(car, false);
    }

    public CarResponse addCar(CarRequest body) {
        Car newCar = CarRequest.getCarEntity(body);
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, true);
    }

    public CarResponse editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,  "Car not found"
                ));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        car = carRepository.save(car);
        return new CarResponse(car, true);
    }

    public ResponseEntity<Boolean> setBestDiscount(int id, int value) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found")
        );
        car.setBestDiscount(value);
        editCar(new CarRequest(car), id);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteCar(int id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
