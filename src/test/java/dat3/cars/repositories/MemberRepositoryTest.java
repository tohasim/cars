package dat3.cars.repositories;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            Member member1 = new Member("Username1", "password1", "email1", "firstName1", "lastName1", "Street1", "city1", "1111");
            Member member2 = new Member("Username2", "password2", "email2", "firstName2", "lastName2", "Street2", "city2", "2222");
            Member member3 = new Member("Username3", "password3", "email3", "firstName3", "lastName3", "Street3", "city3", "3333");
            memberRepository.saveAll(List.of(member1, member2, member3));
        }
    }

    @Test
    public void testAll(){
        double count = memberRepository.count();
        assertEquals(3, count);
    }

    @Test
    public void testDeleteAll(){
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
    }

    @Test
    public void testFindByFirstName(){
        Member member = memberRepository.findByFirstName("firstName1");
        assertEquals("firstName1", member.getFirstName());
    }
}