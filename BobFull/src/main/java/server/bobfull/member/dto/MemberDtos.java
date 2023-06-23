package server.bobfull.member.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bobfull.member.domain.model.Friend;
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
        private Long id;
        private String nickName;
        private String sex;
        private String memberProfileUrl;
        private int studentNum;
        private List<String> allergy;
        private List<String> favor;
        private List<String> nonFavor;
        private int good;
        private int bad;
        private int goodTime;
        private int badTime;
        private int goodTaste;
        private int badTaste;
        private int funny;

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
        private int good;
        private int bad;
        private int goodTime;
        private int badTime;
        private int goodTaste;
        private int badTaste;
        private int funny;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberPutProfileDto {
        private List<String> allergy;
        private List<String> favor;
        private List<String> nonFavor;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberProfileDto {
        private Long memberId;
        private String nickName;
        private int studentNum;
        private String sex;
        private String memberProfileUrl;
        private List<String> rating;
        private List<String> allergy;
        private List<String> favor;
        private List<String> nonFavor;
        public MemberProfileDto(Member member, List<String> rating) {
            this.memberId = member.getId();
            this.nickName = member.getNickName();
            this.studentNum = member.getStudentNum();
            this.sex = member.getSex();
            this.rating = rating;
            this.memberProfileUrl = member.getProfileUrl();
            this.allergy = Arrays.asList(member.getAllergy()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.favor = Arrays.asList(member.getFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
            this.nonFavor = Arrays.asList(member.getNonFavor()
                    .replaceAll("[\\[\\]]", "").split(", "));
        }
    }
    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FriendProfileDto {
        private Long friendId;
        private String friendNickName;
        private String friendProfileUrl;
        public FriendProfileDto(Friend friend){
            this.friendId = friend.getId();
            this.friendNickName= friend.getNickName();
            this.friendProfileUrl = friend.getProfileUrl();
        }
    }

}




