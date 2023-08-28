package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import org.springframework.stereotype.Service;

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
}
