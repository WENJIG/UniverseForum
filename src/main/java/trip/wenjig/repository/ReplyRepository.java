package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Reply;

import java.util.ArrayList;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.isfloorId = ?1 order by r.replyTime desc")
    ArrayList<Reply> findIsFloorAllReplyList(long isFloorId);

}
