package server.bobfull.member.domain.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import server.bobfull.common.model.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import server.bobfull.member.dto.MemberDtos;
import server.bobfull.member.dto.MemberDtos.MemberPostRequestDto;


@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nick_name", unique = true)
    private String nickName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "student_num")
    private int studentNum;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "favor")
    private String favor;

    @Column(name = "non_favor")
    private String nonFavor;

    @Column(name = "profile_url")
    private String profileUrl = "url";

    @Column(name = "fcm_token")
    private String fcmToken = "";

    @Column(name = "good")
    private int good = 0;

    @Column(name = "bad")
    private int bad = 0;

    @Column(name = "good_time")
    private int goodTime = 0;

    @Column(name = "bad_time")
    private int badTime = 0;

    @Column(name = "good_taste")
    private int goodTaste = 0;

    @Column(name = "bad_taste")
    private int badTaste = 0;

    @Column(name = "funny")
    private int funny = 0;

    @Builder
    private Member(String nickName,
                   String sex,
                   int studentNum,
                   String allergy,
                   String favor,
                   String nonFavor){
        this.nickName = nickName;
        this.sex = sex;
        this.studentNum = studentNum;
        this.allergy = allergy;
        this.favor = favor;
        this.nonFavor = nonFavor;
    }

    public static Member create(String nickName,
                                String sex,
                                int studentNum,
                                String allergy,
                                String favor,
                                String nonFavor) {
        return Member.builder()
                .nickName(nickName)
                .sex(sex)
                .studentNum(studentNum)
                .allergy(allergy)
                .favor(favor)
                .nonFavor(nonFavor)
                .build();
    }

    public static Member create(MemberPostRequestDto memberPostRequestDto) {
        return Member.builder()
                .nickName(memberPostRequestDto.getNickName())
                .sex(memberPostRequestDto.getSex())
                .studentNum(memberPostRequestDto.getStudentNum())
                .allergy(memberPostRequestDto.getAllergy())
                .favor(memberPostRequestDto.getFavor())
                .nonFavor(memberPostRequestDto.getNonFavor())
                .build();
    }
}
