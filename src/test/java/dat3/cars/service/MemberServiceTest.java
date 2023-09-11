package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponseList = memberService.getMembers(true, true);
        assertEquals(2, memberResponseList.size(), "Expected 2 members");
        assertNotNull(memberResponseList.get(0).getEdited(), "Show that we get all details");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponseList = memberService.getMembers(false, true);
        assertEquals(2, memberResponseList.size(), "Expected 2 members");
        assertNull(memberResponseList.get(0).getEdited(), "Shows we don't get all details");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse member = memberService.findById("user1");
        assertEquals("user1", member.getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()->memberService.findById("shouldFail"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember saves a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        MemberRequest request = MemberRequest.builder().
                username("user3").
                password("pw3").
                email("email3").
                firstName("fn3").
                lastName("ln3").
                build();
        MemberResponse response = memberService.addMember(request);
        assertEquals("user3", response.getUsername());
        assertTrue(memberRepository.existsById("user3"));
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        MemberRequest request = new MemberRequest();
        request.setUsername("user1");
        request.setEmail("email1");
        request.setPassword("pw1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()->memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("New first name");
        request.setLastName("New last name");

        memberService.editMember(request, m1.getUsername());

        memberRepository.flush();
        MemberResponse response = memberService.findById(m1.getUsername());
        assertEquals("user1", response.getUsername());
        assertEquals("New first name", response.getFirstName());
        assertEquals("New last name", response.getLastName());
        assertEquals("email1", response.getEmail());
        assertEquals("street1", response.getStreet());
        assertEquals("city1", response.getCity());
        assertEquals("zip1", response.getZip());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        MemberRequest request = new MemberRequest();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request, "I dont exist"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("new username");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.editMember(request, "user1"));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cannot change username", exception.getReason());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRanking("user1", 5);
        MemberResponse response = memberService.findById("user1");
        assertEquals(5, response.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.setRanking("user10", 5));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMember("user1");
        assertFalse(memberRepository.existsById("user1"));
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()-> memberService.deleteMember("user10"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
