package dat3.cars.repositories;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.service.CarService;
import dat3.cars.service.MemberService;
import dat3.cars.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;
    boolean isInitialized = false;

    @Autowired
    CarRepository carRepository;

    @Autowired
    MemberRepository memberRepository;

    Reservation reservation1, reservation2;
    Car car1, car2;
    Member member1, member2;


    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            car1 = new Car("brand1", "model1", 100, 10);
            car2 = new Car("brand2", "model2", 200, 20);
            List<Car> cars = List.of(car1, car2);
            carRepository.saveAll(cars);

            member1 = new Member("user1", "password1", "email1", "firstName1",
                    "lastName1", "street1", "city1", "1234");
            member2 = new Member("user2", "password2", "email2", "firstName2",
                    "lastName2", "street2", "city2", "2345");
            List<Member> members = List.of(member1, member2);
            memberRepository.saveAll(members);

            reservation1 = new Reservation(car1, member1, LocalDate.now().plusDays(2));
            reservation2 = new Reservation(car2, member2, LocalDate.now().plusDays(3));
            reservationRepository.saveAll(List.of(reservation1, reservation2));
        }
    }

    @Test
    void testFindCarsByBrandAndModel(){
        String brand = "brand1";
        String model = "model1";
        List<Reservation> reservations = reservationRepository.findAllByCarBrandAndModel(brand, model);
        assertEquals(1, reservations.size());
    }

    @Test
    void findAllCarsWithNoReservation(){
        Car c3 = new Car("brand3", "model3", 300, 30);
        carRepository.save(c3);
        List<Integer> carIds = reservationRepository.findCarsWithNoReservation();
        assertEquals(1, carIds.size());

        assertEquals("brand3", carRepository.findById(carIds.get(0)).get().getBrand());
    }
}