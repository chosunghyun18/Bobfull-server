package server.bobfull.gather.domain.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.bobfull.gather.dto.GatherDtos.GatherCreateRequestDto;
import server.bobfull.member.domain.model.Member;

@Getter
@Entity
@NoArgsConstructor
public class Gather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gather_id")
    private Long id;

    private String bigLocation; // 교내 교외

    private String smallLocation; // 후생관 , 기숙사

    private String wishStore; // 바비든든
    private String wishFood ;

    private LocalDateTime meetTime;

    private Integer maxPrice;
    private String script;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;
    public Gather(GatherCreateRequestDto requestDto, Member member) {
        this.member = member;
        this.bigLocation = requestDto.getBigLocation();
        this.smallLocation = requestDto.getSmallLocation();
        this.wishStore = requestDto.getWishStore();
        this.wishFood = requestDto.getWishFood();
        this.meetTime = requestDto.getMeetTime();
        this.maxPrice = requestDto.getMaxPrice();
        this.script = requestDto.getScript();
    }
}
