package dat3.cars.service;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.ReservationRepository;

import java.time.LocalDate;

public class ReservationService {
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse addReservation(Member member, Car car, LocalDate date){
        return new ReservationResponse(new Reservation(car, member, date));
    }
}
