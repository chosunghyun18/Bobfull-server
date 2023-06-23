package server.bobfull.member.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bobfull.member.domain.model.Friend;
import server.bobfull.member.domain.model.Member;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    List<Friend> findAllByMember(Member member);
}
