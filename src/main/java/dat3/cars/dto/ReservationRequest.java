package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {


    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    int carId;
    String username;

    public ReservationRequest(Reservation reservation) {
        this.reservationDate = reservation.getReservationDate();
        this.carId = reservation.getCar().getId();
        this.username = reservation.getMember().getUsername();
    }
}
