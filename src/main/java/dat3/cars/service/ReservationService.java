package dat3.cars.service;

import dat3.cars.dto.*;
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
import java.util.ArrayList;
import java.util.List;

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
        List<Reservation> reservations = carService.getCarById(body.getCarId()).getReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationDate().isEqual(body.getReservationDate())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved that day");
            }
        }
        Reservation res = reservationRepository.save(new Reservation(car,member, body.getReservationDate()));
        return new ReservationResponse(res);
    }

    public List<CarResponse> findCarsWithNoReservation(){
        List<Integer> carIds = reservationRepository.findCarsWithNoReservation();
        List<CarResponse> returnList = new ArrayList<>();
        for (Integer carId : carIds) {
            returnList.add(carService.findById(carId));
        }
        return returnList;
    }

    public List<ReservationResponse> findReservationsByCar(CarRequest request){
        List<Reservation> reservations = reservationRepository.findByCarBrandAndModel(request.getBrand(), request.getModel());
        List<ReservationResponse> responses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            responses.add(new ReservationResponse(reservation));
        }
        return responses;
    }

    public List<ReservationResponse> getReservationsForUser(String userName) {
        List<ReservationResponse> responses = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findByUsername(userName);
        for (Reservation reservation : reservations) {
            responses.add(new ReservationResponse(reservation));
        }
        return responses;
    }
}