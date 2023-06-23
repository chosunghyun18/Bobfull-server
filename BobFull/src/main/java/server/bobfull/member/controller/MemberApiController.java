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
        return ApiResponse.success(new MemberResponse(savedMember));
    }

    @GetMapping(value = "/total", produces = "application/json;charset=UTF-8")
    public ApiResponse getAllMembers() {
        List<Member> findMembers = memberService.getAll();
        List<MemberResponse> responseList = findMembers.stream().map(MemberResponse::new).collect(Collectors.toList());
        return ApiResponse.success(responseList);
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse getMemberById(@RequestHeader("Authorization") Long memberId) {
        if (memberService.isIdExist(memberId)) {
            Member member = memberService.findByMemberId(memberId);
            return ApiResponse.success(new MemberResponse(member));
        } else {
            return ApiResponse.invaildToken(null);
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
}
