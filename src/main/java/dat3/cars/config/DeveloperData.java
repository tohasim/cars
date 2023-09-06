package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.repositories.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {
    final CarRepository carRepository;
    final MemberRepository memberRepository;

    final ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 100, 10));
        cars.add(new Car("Ford", "Mustang", 150, 15));
        cars.add(new Car("Honda", "Civic", 90, 8));
        cars.add(new Car("Chevrolet", "Impala", 110, 12));
        cars.add(new Car("BMW", "3 Series", 180, 20));
        cars.add(new Car("Mercedes", "E-Class", 200, 18));
        cars.add(new Car("Volkswagen", "Jetta", 95, 9));
        cars.add(new Car("Toyota", "RAV4", 130, 11));
        cars.add(new Car("Ford", "F-150", 170, 14));
        cars.add(new Car("Honda", "Accord", 105, 10));
        cars.add(new Car("Chevrolet", "Cruze", 88, 7));
        cars.add(new Car("BMW", "5 Series", 220, 22));
        cars.add(new Car("Mercedes", "C-Class", 190, 17));
        cars.add(new Car("Volkswagen", "Passat", 98, 8));
        cars.add(new Car("Toyota", "Corolla", 95, 9));
        cars.add(new Car("Ford", "Escape", 120, 10));
        cars.add(new Car("Honda", "Pilot", 140, 12));
        cars.add(new Car("Chevrolet", "Malibu", 100, 10));
        cars.add(new Car("BMW", "X5", 250, 25));
        cars.add(new Car("Mercedes", "S-Class", 300, 30));
        cars.add(new Car("Volkswagen", "Golf", 85, 6));
        cars.add(new Car("Toyota", "Highlander", 150, 13));
        cars.add(new Car("Ford", "Focus", 85, 7));
        cars.add(new Car("Honda", "Fit", 80, 5));
        cars.add(new Car("Chevrolet", "Equinox", 115, 9));
        cars.add(new Car("BMW", "7 Series", 280, 28));
        cars.add(new Car("Mercedes", "GLC", 220, 20));
        cars.add(new Car("Volkswagen", "Tiguan", 110, 8));
        cars.add(new Car("Toyota", "Sienna", 130, 10));
        cars.add(new Car("Ford", "Explorer", 160, 12));
        cars.add(new Car("Honda", "Odyssey", 140, 10));
        cars.add(new Car("Chevrolet", "Silverado", 180, 15));
        cars.add(new Car("BMW", "X3", 200, 18));
        cars.add(new Car("Mercedes", "A-Class", 150, 12));
        cars.add(new Car("Volkswagen", "Atlas", 125, 8));
        cars.add(new Car("Toyota", "4Runner", 170, 13));
        cars.add(new Car("Ford", "Ranger", 150, 10));
        cars.add(new Car("Honda", "CR-V", 125, 9));
        cars.add(new Car("Chevrolet", "Colorado", 140, 11));
        cars.add(new Car("BMW", "X1", 180, 15));
        cars.add(new Car("Mercedes", "CLA", 160, 12));
        cars.add(new Car("Volkswagen", "Arteon", 170, 10));
        cars.add(new Car("Toyota", "Tacoma", 160, 10));
        cars.add(new Car("Ford", "Edge", 140, 8));
        cars.add(new Car("Honda", "HR-V", 105, 6));
        cars.add(new Car("Chevrolet", "Traverse", 155, 10));
        carRepository.saveAll(cars);


        List<Member> members = new ArrayList<>();
        members.add(new Member("johndoe", "secure123", "john.doe@example.com", "John", "Doe", "123 Main St", "Springfield", "12345"));
        members.add(new Member("janesmith", "password456", "jane.smith@example.com", "Jane", "Smith", "456 Elm St", "Greenville", "56789"));
        members.add(new Member("alicejohnson", "passw0rd", "alice.johnson@example.com", "Alice", "Johnson", "789 Oak St", "Riverside", "98765"));
        members.add(new Member("bobwilliams", "mysecretpass", "bob.williams@example.com", "Bob", "Williams", "101 Maple St", "Sunnydale", "54321"));
        members.add(new Member("evebrown", "welcome123", "eve.brown@example.com", "Eve", "Brown", "111 Pine St", "Pleasantville", "13579"));
        members.add(new Member("davidmiller", "12345678", "david.miller@example.com", "David", "Miller", "222 Birch St", "Hometown", "24680"));
        members.add(new Member("sophiadavis", "sophia2023", "sophia.davis@example.com", "Sophia", "Davis", "333 Cedar St", "Metropolis", "86420"));
        memberRepository.saveAll(members);

        List<Reservation> reservations = new ArrayList<>();

        // Hardcoded members and cars for reservations
        Member member1 = members.get(0);
        Car car1 = cars.get(1);

        Member member2 = members.get(1);
        Car car2 = cars.get(2);

        Member member3 = members.get(2);
        Car car3 = cars.get(1);

        Member member4 = members.get(3);
        Car car4 = cars.get(13);

        Member member5 = members.get(4);
        Car car5 = cars.get(5);

        Member member6 = members.get(5);
        Car car6 = cars.get(4);

        Member member7 = members.get(6);
        Car car7 = cars.get(5);

        Member member8 = members.get(0);
        Car car8 = cars.get(10);

        Member member9 = members.get(1);
        Car car9 = cars.get(11);

        Member member10 = members.get(1);
        Car car10 = cars.get(29);

        LocalDate reservationDate = LocalDate.now().plusDays(2); // You can set the reservation date as needed

        // Create 10 reservations with different members and cars
        reservations.add(new Reservation(car1, member1, reservationDate));
        reservations.add(new Reservation(car2, member2, reservationDate));
        reservations.add(new Reservation(car3, member3, reservationDate));
        reservations.add(new Reservation(car4, member4, reservationDate));
        reservations.add(new Reservation(car5, member5, reservationDate));
        reservations.add(new Reservation(car6, member6, reservationDate));
        reservations.add(new Reservation(car7, member7, reservationDate));
        reservations.add(new Reservation(car8, member8, reservationDate));
        reservations.add(new Reservation(car9, member9, reservationDate));
        reservations.add(new Reservation(car10, member10, reservationDate));

        // Now, the 'reservations' list contains 10 reservations with hardcoded values for cars and members.

        reservationRepository.saveAll(reservations);
    }
}
