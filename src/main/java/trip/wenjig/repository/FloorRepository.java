package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Floor;

import java.util.ArrayList;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("select f from Floor f where f.istopicId = ?1 order by f.isfloornum asc")
    ArrayList<Floor> findIsTopicAllFloorList(long isTopicId);

}
