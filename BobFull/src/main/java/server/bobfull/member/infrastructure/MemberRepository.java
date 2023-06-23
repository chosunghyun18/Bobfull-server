package server.bobfull.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import server.bobfull.member.domain.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickName(String nickName);
}
