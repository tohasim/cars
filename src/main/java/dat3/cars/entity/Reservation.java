package dat3.cars.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation extends AdminDetails{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Member member;

    @Column(nullable = false)
    private LocalDate reservationDate;

    public Reservation(Car car, Member member, LocalDate reservationDate) {
        this.car = car;
        this.member = member;
        this.reservationDate = reservationDate;
        car.addReservation(this);
        member.addReservation(this);
    }

}
