package trip.wenjig.entity;

import javax.persistence.*;

@Entity
@Table(name = "un_pic")
public class Picture {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"picId\":")
                .append(picId);
        sb.append(",\"isTopicId\":")
                .append(isTopicId);
        sb.append(",\"isFloor\":")
                .append(isFloor);
        sb.append(",\"isFloorOrder\":")
                .append(isFloorOrder);
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pic_id", insertable = false, updatable = false)
    private long picId;

    @Column(name = "is_topic", nullable = false,updatable = false)
    private long isTopicId;

    @Column(name = "is_floor", nullable = false,updatable = false)
    private long isFloor;

    @Column(name = "is_floor_order", nullable = false, updatable = false)
    private int isFloorOrder;

    public long getPicId() {
        return picId;
    }

    public long getIsTopicId() {
        return isTopicId;
    }

    public void setIsTopicId(long isTopicId) {
        this.isTopicId = isTopicId;
    }

    public long getIsFloor() {
        return isFloor;
    }

    public void setIsFloor(long isFloor) {
        this.isFloor = isFloor;
    }

    public int getIsFloorOrder() {
        return isFloorOrder;
    }

    public void setIsFloorOrder(int isFloorOrder) {
        this.isFloorOrder = isFloorOrder;
    }
}
