package server.bobfull.gather.domain.application;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.bobfull.gather.domain.model.Gather;
import server.bobfull.gather.dto.GatherDtos.*;
import server.bobfull.gather.infrastructure.GatherRepository;
import server.bobfull.member.domain.application.MemberService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GatherService {

    private final GatherRepository gatherRepository;
    private final MemberService memberService;

    public Gather findByGatherId(Long gatherId) {
        return gatherRepository.findById(gatherId)
            .orElseThrow(() -> new EntityNotFoundException("Gather not found by gatherId :" + gatherId));
    }

    @Transactional
    public GatherCreateResponse createGather(Long memberId,GatherCreateRequestDto requestDto) {
        Gather gather = new Gather(requestDto,memberService.findByMemberId(memberId));
        return new GatherCreateResponse(gather);
    }

    public List<GatherResponseDto> getAllGathersForList(Long memberId) {
        memberService.findByMemberId(memberId);
        List<Gather> gathers = gatherRepository.findAll();
        return gathers.stream().map(GatherResponseDto::new).collect(Collectors.toList());
    }
    public GatherDetailDto getGatherDetailByGatherId(Long memberId,Long gatherId) {
        memberService.findByMemberId(memberId);
        Gather gather = findByGatherId(gatherId);
        return new GatherDetailDto(gather);
    }

}
