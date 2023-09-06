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

    int carId;
    String username;

    public ReservationRequest(Reservation r){
        this.carId = r.getCar().getId();
        this.username = r.getMember().getUsername();
        this.reservationDate = r.getReservationDate();
    }
}
