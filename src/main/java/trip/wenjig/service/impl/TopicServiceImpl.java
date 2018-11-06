package trip.wenjig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trip.wenjig.repository.TopicRepository;
import trip.wenjig.entity.Topic;
import trip.wenjig.service.TopicService;

import java.util.ArrayList;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic findById(long id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic findOneMyIdea(String topicTitle, String releaseDate, String postName) {
        return topicRepository.findOneMyIdea(topicTitle, releaseDate, postName);
    }

    @Override
    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public boolean updateTopicFloorNumber(long topicId) {
        return topicRepository.updateTopicFloorNumber(topicId) > 0;
    }

    @Override
    public ArrayList<Topic> findTopicOrderReleaseDate(long isBbsId) {
        return topicRepository.orderByReleaseDate(isBbsId);
    }
}
