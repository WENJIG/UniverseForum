package trip.wenjig.service;

import org.springframework.stereotype.Service;
import trip.wenjig.entity.Topic;

import java.util.ArrayList;

@Service
public interface TopicService {

    Topic findById(long id);

    Topic findOneMyIdea(String topicTitle, String releaseDate, String postName);

    void addTopic(Topic topic);

    boolean updateTopicFloorNumber(long topicId);

    ArrayList<Topic> findTopicOrderReleaseDate(long isBbsId);

}
