package server.bobfull.gather.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bobfull.gather.domain.model.Gather;
import server.bobfull.member.domain.model.Member;

@Repository
public interface GatherRepository extends JpaRepository<Gather,Long> {
    Gather findByMember(Member member);
}
