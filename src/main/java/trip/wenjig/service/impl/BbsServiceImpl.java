package trip.wenjig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trip.wenjig.repository.BbsRepository;
import trip.wenjig.entity.Bbs;
import trip.wenjig.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

    @Autowired
    private BbsRepository bbsRepository;

    @Override
    public void addBbs(Bbs bbs) {
        bbsRepository.save(bbs);
    }

    @Override
    public boolean updateBbsTopicNumber(long bbsId) {
        return bbsRepository.updateBbsTopicNumber(bbsId) > 0;
    }

    @Override
    public Bbs findByBbsName(String name) {
        return bbsRepository.findByBbsName(name);
    }
}
