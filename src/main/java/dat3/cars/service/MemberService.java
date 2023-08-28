package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> repositoryList = memberRepository.findAll();
        List<MemberResponse> responseList = new ArrayList<>();
        for (Member member : repositoryList) {
            responseList.add(new MemberResponse(member, includeAll));
        }
        return responseList;
    }

    public MemberResponse addMember(MemberRequest body) {
        Member newMember = MemberRequest.getMemberEntity(body);
        if(memberRepository.existsById(body.getUsername())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user already exists");
        }
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, true);
    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        if(!body.getUsername().equals(username)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }


    public MemberResponse findById(String username) {
        Member member = memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        return new MemberResponse(member, false);
    }

    public void deleteMember(String username) {
        Member memberToDelete = memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        memberRepository.delete(memberToDelete);
    }

    public ResponseEntity<Boolean> setRanking(String username, int value) {
        //TODO: Make setRanking method
        return ResponseEntity.ok(true);
    }
}
