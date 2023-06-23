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
    public static class MemberResponse {
        Long id;
        String nickName;
        String sex;
        int studentNum;
        String allergy;
        String favor;
        String nonFavor;

        public MemberResponse(Member member) {
            this.id = id;
            this.nickName = nickName;
            this.sex = sex;
            this.studentNum = studentNum;
            this.allergy = allergy;
            this.favor = favor;
            this.nonFavor = nonFavor;
        }
    }
}
