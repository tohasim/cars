package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Anonymous
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(true);
    }

    //Anonymous
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id){
        return carService.findById(id);
    }

    //Admin only
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Admin only
    @PutMapping("/{id}")
    CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body, id);
    }

    //Admin only
    @PatchMapping("/best-discount/{id}/{value}")
    ResponseEntity<Boolean> setBestDiscount(@PathVariable int id, @PathVariable int value){
        return carService.setBestDiscount(id, value);
    }

    //Admin only
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCarById(@PathVariable int id){
        return carService.deleteCar(id);
    }

}
