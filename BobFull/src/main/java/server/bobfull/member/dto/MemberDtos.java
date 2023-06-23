package server.bobfull.member.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bobfull.member.domain.model.Member;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDtos {

    @Data
    @NoArgsConstructor
    public static class MemberPostRequestDto {
        String nickName;
        String sex;
        int studentNum;
        String allergy;
        String favor;
        String nonFavor;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberResponseDto {
        Long id;
        String nickName;
        String sex;
        int studentNum;
        String allergy;
        String favor;
        String nonFavor;
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
            this.studentNum = member.getStudentNum();
            this.allergy = member.getAllergy();
            this.favor = member.getFavor();
            this.nonFavor = member.getNonFavor();
            this.good = member.getGood();
            this.bad = member.getBad();
            this.goodTime = member.getGoodTime();
            this.badTime = member.getBadTime();
            this.goodTaste = member.getGoodTaste();
        }
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class MemberPostReviewDto {
        int good;
        int bad;
        int goodTime;
        int badTime;
        int goodTaste;
        int badTaste;
        int funny;
    }
}
