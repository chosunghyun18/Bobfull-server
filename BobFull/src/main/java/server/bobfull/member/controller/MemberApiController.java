package server.bobfull.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.bobfull.common.dto.ApiResponse;
import server.bobfull.member.domain.application.MemberService;
import server.bobfull.member.domain.model.Member;
import server.bobfull.member.dto.MemberDtos;
import server.bobfull.member.dto.MemberDtos.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse postMember(@Valid @RequestBody MemberPostRequestDto request) throws Exception {
        if(memberService.isNickRedundant(request.getNickName())) {
            return ApiResponse.success(false);
        }
        Member savedMember = memberService.saveMember(request);
        return ApiResponse.success(new MemberResponseDto(savedMember));
    }

    @GetMapping(value = "/total", produces = "application/json;charset=UTF-8")
    public ApiResponse getAllMembers() {
        List<Member> findMembers = memberService.getAll();
        List<MemberResponseDto> responseList =
                findMembers.stream().map(MemberResponseDto::new).collect(Collectors.toList());
        return ApiResponse.success(responseList);
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse getMemberById(@RequestHeader("Authorization") Long memberId) {
        try {
            Member member = memberService.findByMemberId(memberId);
            return ApiResponse.success(new MemberResponseDto(member));
        } catch(NoSuchElementException e) {
            return ApiResponse.invaildToken(false);
        }
    }

    @GetMapping(value = "/nick",produces = "application/json;charset=UTF-8")
    public ApiResponse getMemberByNickName(@RequestParam("nickName") String nickName) {
        try {
            Member member = memberService.findMemberByNick(nickName);
            return ApiResponse.success(new MemberResponseDto(member));
        } catch (NoSuchElementException e) {
            return ApiResponse.success(false);
        }
    }

    @DeleteMapping(value = "/profile", produces = "application/json;charset=UTF-8")
    public ApiResponse deleteMemberById(@RequestHeader("Authorization") Long memberId) {
        if (memberService.isIdExist(memberId)) {
            memberService.deleteMember(memberId);
            return ApiResponse.success("Delete Success");
        } else {
            return ApiResponse.invaildToken(false);
        }
    }

    @PostMapping(value = "/review", produces = "application/json;charset=UTF-8")
    public ApiResponse postReview(@RequestHeader("Authorization") Long memberId,
                                  @RequestBody MemberPostReviewDto request) {
        memberService.addReview(memberId, request);
        return ApiResponse.success(true);
    }

    @PutMapping(value = "/profile", produces = "application/json;charset=UTF-8")
    public ApiResponse putProfile(@RequestHeader("Authorization") Long memberId,
                                  @RequestBody MemberPutProfileDto memberPutProfileDto) {
        try {
            Member member = memberService.replaceProfileByMemberId(memberId, memberPutProfileDto);
            return ApiResponse.success(new MemberResponseDto(member));
        }catch (NoSuchElementException e) {
            return ApiResponse.success(false);
        }
    }

    @PutMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse putFcmToken(@RequestHeader("Authorization") Long memberId,
                                   @RequestParam("fcm") String fcm) {
        try {
            memberService.modifyTokenByGivenToken(memberId, fcm);
        }catch (NoSuchElementException e) {
            return ApiResponse.success(false);
        }
        return ApiResponse.success(true);
    }
}
