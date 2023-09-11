package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationServiceTest {

    @Autowired
    ReservationRepository reservationRepository;
    ReservationService reservationService;

    @Autowired
    CarRepository carRepository;
    CarService carService;

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Reservation reservation1, reservation2;
    Car car1, car2;
    Member member1, member2;


    @BeforeEach
    void setUp() {
        car1 = new Car("brand1", "model1", 100, 10);
        car2 = new Car("brand2", "model2", 200, 20);
        List<Car> cars = List.of(car1, car2);
        carRepository.saveAll(cars);
        carService = new CarService(carRepository);

        member1 = new Member("user1", "password1", "email1", "firstName1",
                "lastName1", "street1", "city1", "1234");
        member2 = new Member("user2", "password2", "email2", "firstName2",
                "lastName2", "street2", "city2", "2345");
        List<Member> members = List.of(member1, member2);
        memberRepository.saveAll(members);
        memberService = new MemberService(memberRepository);


        reservation1 = new Reservation(car1, member1, LocalDate.now().plusDays(2));
        reservation2 = new Reservation(car2, member2, LocalDate.now().plusDays(3));
        reservationRepository.saveAll(List.of(reservation1, reservation2));
        reservationService = new ReservationService(carService, memberService, reservationRepository);
    }

    @Test
    void testReserveCarGoodPath() {
        int carId = carService.getCars(true).get(0).getId();
        String username = memberService.getMembers(true, true).get(0).getUsername();
        ReservationRequest request = new ReservationRequest(LocalDate.now(), carId, username);
        ReservationResponse response = reservationService.reserveCar(request);
        assertEquals(carId, response.getCarId());
    }

    @Test
    void testReserveCar_CarNotFound(){
        String username = memberService.getMembers(true, true).get(0).getUsername();
        ReservationRequest request = new ReservationRequest(LocalDate.now(), 9999, username);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.reserveCar(request)
                );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Car not found", exception.getReason());
    }

    @Test
    void testReserveCar_MemberNotFound(){
        int carId = carService.getCars(true).get(0).getId();
        String username = "Not found";
        ReservationRequest request = new ReservationRequest(LocalDate.now(), carId, username);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.reserveCar(request)
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Member with this username does not exist", exception.getReason());
    }


    @Test
    void testReserveCar_CarAlreadyReserved(){
        ReservationRequest request = new ReservationRequest(LocalDate.now().plusDays(2), car1.getId(), member2.getUsername());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.reserveCar(request)
                );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Car is already reserved that day", exception.getReason());
    }

    @Test
    void testReserveCar_DateInPast(){
        ReservationRequest request = new ReservationRequest(LocalDate.now().minusDays(2), car1.getId(), member2.getUsername());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> reservationService.reserveCar(request)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Date in past not allowed", exception.getReason());
    }

    @Test
    void testFindCarsWithNoReservationTest(){
        Car car = new Car("noRes", "noRes", 100, 10);
        carService.addCar(new CarRequest(car));
        List<CarResponse> responses = reservationService.findCarsWithNoReservation();
        assertEquals(1, responses.size());
    }

    @Test
    void testFindReservationsByCar(){
        CarRequest request = new CarRequest(car1);
        Reservation reservation3 = new Reservation(car1, member1, LocalDate.now().plusDays(4));
        Reservation reservation4 = new Reservation(car1, member1, LocalDate.now().plusDays(5));
        Reservation reservation5 = new Reservation(car1, member1, LocalDate.now().plusDays(6));
        reservationRepository.saveAll(List.of(reservation3, reservation4, reservation5));
        List<ReservationResponse> responses = reservationService.findReservationsByCar(request);
        assertEquals(4, responses.size());

    }
}