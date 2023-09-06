package dat3.cars.service;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    public ReservationResponse addReservation(ReservationRequest body){
        if (body.getReservationDate().isBefore(LocalDate.now())){
            
        }
        return null;
    }
}
