package server.bobfull.member.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.bobfull.member.domain.model.Member;
import server.bobfull.member.dto.MemberDtos;
import server.bobfull.member.dto.MemberDtos.MemberPostRequestDto;
import server.bobfull.member.dto.MemberDtos.MemberPostReviewDto;
import server.bobfull.member.dto.MemberDtos.MemberPutProfileDto;
import server.bobfull.member.infrastructure.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(MemberPostRequestDto memberPostRequestDto){
        Member member = Member.create(memberPostRequestDto);
        memberRepository.save(member);
        return member;
    }

    public Member findByMemberId(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found by memberId :" + memberId));
    }

    public String findFcmTokenByMemberId(Long memberId){
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found by memberId :" + memberId))
                .getFcmToken();
    }

    public boolean isNickRedundant(String nickName) {
        return memberRepository.findByNickName(nickName).isPresent();
    }

    public Member findMemberByNick(String nickName) {
        return memberRepository.findByNickName(nickName)
                .orElseThrow(() -> new NoSuchElementException("Member not found by nickName :" + nickName));
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = findByMemberId(id);
        memberRepository.deleteById(member.getId());
    }

    public boolean isIdExist(Long memberId) {
        return memberRepository.findById(memberId).isPresent();
    }

    @Transactional
    public void addReview(Long memberId, MemberPostReviewDto request) { findByMemberId(memberId).addReview(request); }

    public Member replaceProfileByMemberId(Long memberId, MemberPutProfileDto memberPutProfileDto) {
        return findByMemberId(memberId).changeProfile(memberPutProfileDto);
    }

    @Transactional
    public void modifyTokenByGivenToken(Long memberId, String fcm) { findByMemberId(memberId).modifyFcmToken(fcm); }
}
