package server.bobfull.gather.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bobfull.gather.domain.model.Gather;

@Repository
public interface GatherRepository extends JpaRepository<Gather,Long> {

}
