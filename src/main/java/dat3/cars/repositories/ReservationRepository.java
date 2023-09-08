package dat3.cars.repositories;

import dat3.cars.entity.Car;
import dat3.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query(
            value = "SELECT r.* FROM reservation r INNER JOIN car c ON r.car_id = c.id WHERE c.car_brand = ?1 AND c.car_model = ?2",
            nativeQuery = true
    )
    List<Reservation> findAllByCarBrandAndModel(String brand, String model);

    @Query(
            value = "SELECT c.id FROM car c LEFT JOIN reservation r ON c.id = r.car_id where reservation_date is null",
            nativeQuery = true
    )
    List<Integer> findCarsWithNoReservation();
}
