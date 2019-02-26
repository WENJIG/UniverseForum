package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Floor;

import java.util.ArrayList;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query(nativeQuery = true, value = "select * from un_floor where istopic_id = ?1 order by isfloornum asc")
    ArrayList<Floor> findIsTopicAllFloorList(long isTopicId);

}
