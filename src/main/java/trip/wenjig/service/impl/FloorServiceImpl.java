package trip.wenjig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trip.wenjig.entity.Floor;
import trip.wenjig.repository.FloorRepository;
import trip.wenjig.service.FloorService;

import java.util.ArrayList;

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Override
    public void addFloor(Floor floor) {
        floorRepository.save(floor);
    }

    @Override
    public ArrayList<Floor> findIsTopicAllFloorList(long isTopicId) {
        return floorRepository.findIsTopicAllFloorList(isTopicId);
    }
}
