package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Bbs;

import javax.transaction.Transactional;

@Repository
public interface BbsRepository extends JpaRepository<Bbs, Long> {

    Bbs findByBbsName(String name);

    @Modifying
    @Transactional
    @Query("update Bbs b set b.bbsTopicNum=b.bbsTopicNum+1 where b.bbsId=?1")
    int updateBbsTopicNumber(long bbsId);

}
