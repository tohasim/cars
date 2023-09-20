package dat3.cars.api;

import dat3.cars.dto.CarResponse;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest res){
        ReservationResponse r = service.reserveCar(res);
        return r;
    }

    @GetMapping("/carsWithNoReservations")
    List<CarResponse> getCarsWithNoReservations(){
        List<CarResponse> cars = service.findCarsWithNoReservation();
        return cars;
    }
}
