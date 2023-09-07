package dat3.cars.service;

import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberService memberService;
    CarService carService;

    public ReservationService(CarService carService, MemberService memberService, ReservationRepository reservationRepository) {
        this.carService = carService;
        this.memberService = memberService;
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse reserveCar(ReservationRequest body){
        Member member = memberService.getMemberByUsername(body.getUsername());
        Car car = carService.getCarById(body.getCarId());
        if(body.getReservationDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
        }
        for (Reservation reservation : carService.getCarById(body.getCarId()).getReservations()) {
            if (reservation.getReservationDate().isEqual(body.getReservationDate())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved that day");
            }
        }
        Reservation res = reservationRepository.save(new Reservation(car,member, body.getReservationDate()));
        return new ReservationResponse(res);
    }

}