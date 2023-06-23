package server.bobfull.member.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bobfull.member.domain.model.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDtos {

    @Data
    @NoArgsConstructor
    public static class MemberPostRequestDto {
        String nickName;
        String sex;
        int studentNum;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberResponseDto {
        Long id;
        String nickName;
        String sex;
        String memberProfileUrl;
        int studentNum;
        List<String> allergy;
        List<String> favor;
        List<String> nonFavor;
        int good;
        int bad;
        int goodTime;
        int badTime;
        int goodTaste;
        int badTaste;
        int funny;

        public MemberResponseDto(Member member) {
            this.id = member.getId();
            this.nickName = member.getNickName();
            this.sex = member.getSex();
            this.memberProfileUrl = member.getProfileUrl();
            this.studentNum = member.getStudentNum();
            this.allergy = Stream.of(member.getAllergy()).collect(Collectors.toList());
            this.favor = Stream.of(member.getFavor()).collect(Collectors.toList());
            this.nonFavor = Stream.of(member.getNonFavor()).collect(Collectors.toList());
            this.good = member.getGood();
            this.bad = member.getBad();
            this.goodTime = member.getGoodTime();
            this.badTime = member.getBadTime();
            this.goodTaste = member.getGoodTaste();
        }
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberPostReviewDto {
        int good;
        int bad;
        int goodTime;
        int badTime;
        int goodTaste;
        int badTaste;
        int funny;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberPutProfileDto {
        List<String> allergy;
        List<String> favor;
        List<String> nonFavor;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberProfileDto {
        String nickName;
        List<String> rating = new ArrayList<>();


    }
}
