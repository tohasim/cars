package dat3.cars.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Member member;

    private LocalDate reservationDate, rentalDate;

    public Reservation(Car car, Member member, LocalDate reservationDate, LocalDate rentalDate) {
        this.car = car;
        this.member = member;
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

}
