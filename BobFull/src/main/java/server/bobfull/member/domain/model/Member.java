package server.bobfull.member.domain.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import server.bobfull.common.model.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import server.bobfull.member.dto.MemberDtos;
import server.bobfull.member.dto.MemberDtos.MemberPostRequestDto;
import server.bobfull.member.dto.MemberDtos.MemberPostReviewDto;
import server.bobfull.member.dto.MemberDtos.MemberPutProfileDto;


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
    private String allergy = "";

    @Column(name = "favor")
    private String favor = "";

    @Column(name = "non_favor")
    private String nonFavor = "";

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
                   int studentNum){
        this.nickName = nickName;
        this.sex = sex;
        this.studentNum = studentNum;
    }

    public void changeProfile(MemberPutProfileDto memberPutProfileDto) {
        this.allergy = memberPutProfileDto.getAllergy();
        this.favor = memberPutProfileDto.getFavor();
        this.nonFavor = memberPutProfileDto.getNonFavor();
    }

    public static Member create(MemberPostRequestDto memberPostRequestDto) {
        return Member.builder()
                .nickName(memberPostRequestDto.getNickName())
                .sex(memberPostRequestDto.getSex())
                .studentNum(memberPostRequestDto.getStudentNum())
                .build();
    }

    public void addReview(MemberPostReviewDto memberPostReviewDto) {
        this.good += memberPostReviewDto.getGood();
        this.bad += memberPostReviewDto.getBad();
        this.goodTime += memberPostReviewDto.getGoodTime();
        this.badTime += memberPostReviewDto.getBadTime();
        this.goodTaste += memberPostReviewDto.getGoodTaste();
        this.badTaste += memberPostReviewDto.getBadTaste();
        this.funny += memberPostReviewDto.getFunny();
    }

    public void modifyFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
