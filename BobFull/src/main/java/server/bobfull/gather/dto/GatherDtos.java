package server.bobfull.gather.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bobfull.gather.domain.model.Gather;

@Data
public class GatherDtos {
    @Data
    public static class GatherResponseDto {
        private Long gatherId;
        //memberInfo
        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String memberImageUrl;

        private String bigLocation; // 교내 교외

        private String smallLocation; // 후생관 , 기숙사

        private String wishStore; // 바비든든
        private String wishFood ;

        private LocalDateTime meetTime;

        private Integer maxPrice;
        private String script;

        public GatherResponseDto(Gather gather) {
            this.gatherId = gather.getId();
            this.memberId = gather.getMember().getId();
            this.memberNickName = gather.getMember().getNickName();
            this.memberSex = gather.getMember().getSex();
            this.memberImageUrl = gather.getMember().getProfileUrl();
            this.bigLocation = gather.getBigLocation();
            this.smallLocation = gather.getSmallLocation();
            this.wishStore = gather.getWishStore();
            this.wishFood = gather.getWishFood();
            this.meetTime = gather.getMeetTime();
            this.maxPrice = gather.getMaxPrice();
            this.script = gather.getScript();
        }
    }
    @Data
    @NoArgsConstructor
    public static class GatherCreateResponse{
        private Long gatherId;

        private Long memberId;
        private String memberNickName;
        private String memberProfileUrl;
        private int memberStudentNum;
        private String bigLocation; // 교내 교외

        private String smallLocation; // 후생관 , 기숙사

        private String wishStore; // 바비든든
        private String wishFood ;

        private LocalDateTime meetTime;

        private Integer maxPrice;
        private String script;
        public GatherCreateResponse(Gather gather) {
            this.gatherId = gather.getId();
            this.memberId = gather.getMember().getId();
            this.memberNickName = gather.getMember().getNickName();
            this.memberProfileUrl = gather.getMember().getProfileUrl();
            this.memberStudentNum =gather.getMember().getStudentNum();
            this.bigLocation = gather.getBigLocation();
            this.smallLocation = gather.getSmallLocation();
            this.wishStore = gather.getWishStore();
            this.wishFood = gather.getWishFood();
            this.meetTime = gather.getMeetTime();
            this.maxPrice = gather.getMaxPrice();
            this.script = gather.getScript();
        }

    }
    @Data
    @NoArgsConstructor
    public static class GatherCreateRequestDto{
        private String bigLocation; // 교내 교외

        private String smallLocation; // 후생관 , 기숙사

        private String wishStore; // 바비든든
        private String wishFood ;

        private LocalDateTime meetTime;

        private Integer maxPrice;
        private String script;

    }

    @Data
    @NoArgsConstructor
    public static class GatherDetailDto{
        private Long gatherId;

        private Long memberId;
        private String memberNickName;
        private String memberProfileUrl;
        private int memberStudentNum;

        private String bigLocation; // 교내 교외

        private String smallLocation; // 후생관 , 기숙사

        private String wishStore; // 바비든든
        private String wishFood ;

        private LocalDateTime meetTime;

        private Integer maxPrice;
        private String script;
        public GatherDetailDto(Gather gather) {
            this.gatherId = gather.getId();
            this.memberId = gather.getMember().getId();
            this.memberNickName = gather.getMember().getNickName();
            this.memberProfileUrl = gather.getMember().getProfileUrl();
            this.memberStudentNum =gather.getMember().getStudentNum();
            this.bigLocation = gather.getBigLocation();
            this.smallLocation = gather.getSmallLocation();
            this.wishStore = gather.getWishStore();
            this.wishFood = gather.getWishFood();
            this.meetTime = gather.getMeetTime();
            this.maxPrice = gather.getMaxPrice();
            this.script = gather.getScript();
        }
    }

}


