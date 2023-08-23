package dat3.cars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    private String username;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String zip;
    private String city;

    private int ranking;

    private boolean approved;


    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;
    public Member(String user, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        this.username = user;
        this.password= password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

}
