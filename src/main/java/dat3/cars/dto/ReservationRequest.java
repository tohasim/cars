package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {


    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate reservationDate, rentalDate;

    private Car car;
    private Member member;

    public static Reservation getReservationEntity(ReservationRequest r){
        return new Reservation(r.car, r.member, r.reservationDate, r.rentalDate);
    }

    public ReservationRequest(Reservation r){
        this.car = r.getCar();
        this.member = r.getMember();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
    }
}
