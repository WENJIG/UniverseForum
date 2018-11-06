package trip.wenjig.service;

import org.springframework.stereotype.Service;
import trip.wenjig.entity.Bbs;

@Service
public interface BbsService {

    void addBbs(Bbs bbs);

    boolean updateBbsTopicNumber(long bbsId);

    Bbs findByBbsName(String name);

}
