package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/members")
class MemberController {

    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //Admin only
    @GetMapping("/admin")
    List<MemberResponse> getMembersAdmin(){
        return memberService.getMembers(true, true);
    }

    //Anonymous
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false, true);
    }

    //Admin only
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username){
        return memberService.findById(username);
    }

    //Anonymous
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Admin only since this could be done for any user
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(body, username);
    }

    //Admin only
    @PatchMapping("/ranking/{username}/{value}")
    ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
        return memberService.setRanking(username, value);
    }

    //Admin only since this could be done for any user
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteMember(username);
    }

}

