package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "un_floor")
public class Floor implements Serializable {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"floorId\":")
                .append(floorId);
        sb.append(",\"floorContext\":\"")
                .append(floorContext).append('\"');
        sb.append(",\"postUsername\":\"")
                .append(postUsername).append('\"');
        sb.append(",\"postDate\":\"")
                .append(postDate).append('\"');
        sb.append(",\"istopicId\":")
                .append(istopicId);
        sb.append(",\"isfloornum\":")
                .append(isfloornum);
        sb.append(",\"repliesnum\":")
                .append(repliesnum);
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_id", insertable = false, updatable = false)
    private long floorId;

    @Column(name = "floor_context", nullable = false)
    private String floorContext;

    @Column(name = "postUsername", nullable = false, length = 20)
    private String postUsername;

    @Column(name = "post_date", nullable = false, insertable = false, updatable = false)
    private String postDate;

    @Column(name = "istopic_id", nullable = false)
    private long istopicId;

    @Column(name = "isfloornum", nullable = false)
    private long isfloornum;

    @Column(name = "repliesnum", nullable = false)
    private long repliesnum;

    public long getFloorId() {
        return floorId;
    }

    public String getFloorContext() {
        return floorContext;
    }

    public void setFloorContext(String floorContext) {
        this.floorContext = floorContext;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public long getIstopicId() {
        return istopicId;
    }

    public void setIstopicId(long istopicId) {
        this.istopicId = istopicId;
    }

    public long getIsfloornum() {
        return isfloornum;
    }

    public void setIsfloornum(long isfloornum) {
        this.isfloornum = isfloornum;
    }

    public long getRepliesnum() {
        return repliesnum;
    }

    public void setRepliesnum(long repliesnum) {
        this.repliesnum = repliesnum;
    }
}
