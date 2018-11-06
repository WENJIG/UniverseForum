package trip.wenjig.service;

import trip.wenjig.entity.Reply;

import java.util.ArrayList;

public interface ReplyService {

    void addReply(Reply reply);

    ArrayList<Reply> findIsFloorAllReplyList(long isFloorId);

}
