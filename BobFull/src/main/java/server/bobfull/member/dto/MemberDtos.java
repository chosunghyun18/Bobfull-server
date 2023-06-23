package server.bobfull.member.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bobfull.member.domain.model.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            this.allergy = Arrays.asList(member.getAllergy()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.favor = Arrays.asList(member.getFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.nonFavor = Arrays.asList(member.getNonFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
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
        int studentNum;
        String sex;
        List<String> rating;
        List<String> allergy;
        List<String> favor;
        List<String> nonFavor;
        public MemberProfileDto(Member member, List<String> rating) {
            this.nickName = member.getNickName();
            this.studentNum = member.getStudentNum();
            this.sex = member.getSex();
            this.rating = rating;
            this.allergy = Arrays.asList(member.getAllergy()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.favor = Arrays.asList(member.getFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.nonFavor = Arrays.asList(member.getNonFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
        }
    }
}
