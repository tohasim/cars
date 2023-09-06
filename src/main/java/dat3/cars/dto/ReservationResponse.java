package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private Integer id;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate reservationDate, rentalDate;

    private Car car;
    private Member member;

    public ReservationResponse(Reservation r){
        this.id = r.getId();
        this.reservationDate = r.getReservationDate();
        this.car = r.getCar();
        this.member = r.getMember();
    }

}
