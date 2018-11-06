package trip.wenjig.service;

import trip.wenjig.entity.Floor;

import java.util.ArrayList;

public interface FloorService {

    void addFloor(Floor floor);

    ArrayList<Floor> findIsTopicAllFloorList(long isTopicId);

}
