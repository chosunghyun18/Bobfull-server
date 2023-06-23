package server.bobfull.gather.domain.application;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.bobfull.gather.domain.model.Gather;
import server.bobfull.gather.dto.GatherDtos.*;
import server.bobfull.gather.infrastructure.GatherRepository;
import server.bobfull.infra.google.fcm.FCMService;
import server.bobfull.member.domain.application.MemberService;
import server.bobfull.member.domain.model.Member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GatherService {

    private final GatherRepository gatherRepository;
    private final MemberService memberService;
    private final FCMService fcmService;

    public Gather findByGatherId(Long gatherId) {
        return gatherRepository.findById(gatherId)
            .orElseThrow(() -> new EntityNotFoundException("Gather not found by gatherId :" + gatherId));
    }

    @Transactional
    public GatherCreateResponse createGather(Long memberId,GatherCreateRequestDto requestDto) {
        Gather gather = new Gather(requestDto,memberService.findByMemberId(memberId));
        gatherRepository.save(gather);
        return new GatherCreateResponse(gather);
    }

    public List<GatherResponseDto> getAllGathersForList(Long memberId,String bigLocation,String smallLocation) {
        memberService.findByMemberId(memberId);
        List<Gather> gathers = gatherRepository.findAll();
        List<Gather> filteredGathers = gathers.stream()
            .filter(gather -> gather.getBigLocation().equals(bigLocation)).toList();

        List<Gather> gathersResult = filteredGathers.stream()
            .filter(gather -> gather.getSmallLocation().equals(smallLocation)).toList();

        return gathersResult.stream()
            .map(GatherResponseDto::new)
            .collect(Collectors.toList());
    }
    public GatherDetailDto getGatherDetailByGatherId(Long memberId,Long gatherId) {
        memberService.findByMemberId(memberId);
        Gather gather = findByGatherId(gatherId);
        return new GatherDetailDto(gather);
    }
    public GatherDetailDto getGatherDetailByMemberId(Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        Gather gather = gatherRepository.findByMember(member);
        return new GatherDetailDto(gather);
    }
    // 밥약 참여하기
    public Boolean proposalJoin(Long memberId, Long gatherId) {
        Member friend = memberService.findByMemberId(memberId); // 요청자
        Gather gather = findByGatherId(gatherId);
        memberService.saveFriend(gather.getMember(),friend.getNickName(),friend.getProfileUrl());
        try {
            fcmService.sendMessageTo(gather.getMember().getFcmToken(), "JOIN", memberId.toString());
            return true;
        }catch (IOException e) {
            return false;
        }

    }

    public Boolean proposalConfirm(Long memberId, Boolean confirm,Long confirmMemberId,Long friendId) {
        memberService.findByMemberId(memberId); // 승낙
        memberService.deleteFriendById(friendId);
        Member requestedMember =memberService.findByMemberId(confirmMemberId);
        try {
        fcmService.sendMessageTo(requestedMember.getFcmToken(),"RESULT",confirm.toString());
            return true;
        }catch (IOException e) {
            return false;
        }
    }
    @Transactional
    public Boolean deleteGatherByGatherId(Long memberId, Long gatherId) {
        Member member = memberService.findByMemberId(memberId);
        Gather gather = findByGatherId(gatherId);
        if(Objects.equals(member.getId(), gather.getMember().getId())){
            gatherRepository.delete(gather);
            return true;
        }
        else return false;
    }
}
