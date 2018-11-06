package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Topic;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select t from Topic t where t.topicId=?1")
    Topic findById(long id);

    @Query("select t from Topic t where t.topicTitle=?1 and t.releaseDate=?2 and t.postUserName=?3")
    Topic findOneMyIdea(String topicTitle, String releaseDate, String postName);

    @Query("select t from Topic t where t.topicBbsid=?1 order by t.releaseDate desc")
    ArrayList<Topic> orderByReleaseDate(long isBbsId);

    @Modifying
    @Transactional
    @Query("update Topic t set t.topicFloorsNum=t.topicFloorsNum+1 where t.topicId=?1")
    int updateTopicFloorNumber(long topicId);

}
