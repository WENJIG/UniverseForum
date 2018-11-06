package trip.wenjig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trip.wenjig.entity.Reply;
import trip.wenjig.repository.ReplyRepository;
import trip.wenjig.service.ReplyService;

import java.util.ArrayList;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public void addReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public ArrayList<Reply> findIsFloorAllReplyList(long isFloorId) {
        return replyRepository.findIsFloorAllReplyList(isFloorId);
    }

}
