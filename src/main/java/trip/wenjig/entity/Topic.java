package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "un_topic")
public class Topic implements Serializable {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"topicId\":")
                .append(topicId);
        sb.append(",\"topicTitle\":\"")
                .append(topicTitle).append('\"');
        sb.append(",\"topicContext\":\"")
                .append(topicContext).append('\"');
        sb.append(",\"topicBbsid\":")
                .append(topicBbsid);
        sb.append(",\"topicFloorsNum\":")
                .append(topicFloorsNum);
        sb.append(",\"locktopic\":")
                .append(locktopic);
        sb.append(",\"postUserName\":\"")
                .append(postUserName).append('\"');
        sb.append(",\"releaseDate\":\"")
                .append(releaseDate).append('\"');
        sb.append(",\"topicHits\":")
                .append(topicHits);
        sb.append(",\"lastpostMsg\":\"")
                .append(lastpostMsg).append('\"');
        sb.append(",\"lastpostTime\":\"")
                .append(lastpostTime).append('\"');
        sb.append(",\"lastpostUsername\":\"")
                .append(lastpostUsername).append('\"');
        sb.append(",\"isBest\":")
                .append(isBest);
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id", insertable = false, updatable = false)
    private long topicId;

    @Column(name = "topic_title", length = 50)
    private String topicTitle;

    @Column(name = "topic_context", nullable = false)
    private String topicContext;

    @Column(name = "topic_bbsid", nullable = false)
    private long topicBbsid;

    @Column(name = "topic_floorsnum", nullable = false)
    private long topicFloorsNum;

    @Column(name = "locktopic", nullable = false)
    private int locktopic;

    @Column(name = "post_user", nullable = false, length = 20)
    private String postUserName;

    @Column(name = "release_date", nullable = false, updatable = false)
    private String releaseDate;

    @Column(name = "topic_hits", nullable = false)
    private int topicHits;

    @Column(name = "lastpost_msg", length = 500)
    private String lastpostMsg;

    @Column(name = "lastpost_time")
    private String lastpostTime;

    @Column(name = "lastpost_username", length = 20)
    private String lastpostUsername;

    @Column(name = "isbest")
    private int isBest;

    public long getTopicId() {
        return topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContext() {
        return topicContext;
    }

    public void setTopicContext(String topicContext) {
        this.topicContext = topicContext;
    }

    public long getTopicBbsid() {
        return topicBbsid;
    }

    public void setTopicBbsid(long topicBbsid) {
        this.topicBbsid = topicBbsid;
    }

    public long getTopicFloorsNum() {
        return topicFloorsNum;
    }

    public void setTopicFloorsNum(long topicFloorsNum) {
        this.topicFloorsNum = topicFloorsNum;
    }

    public int getLocktopic() {
        return locktopic;
    }

    public void setLocktopic(int locktopic) {
        this.locktopic = locktopic;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTopicHits() {
        return topicHits;
    }

    public void setTopicHits(int topicHits) {
        this.topicHits = topicHits;
    }

    public String getLastpostMsg() {
        return lastpostMsg;
    }

    public void setLastpostMsg(String lastpostMsg) {
        this.lastpostMsg = lastpostMsg;
    }

    public String getLastpostTime() {
        return lastpostTime;
    }

    public void setLastpostTime(String lastpostTime) {
        this.lastpostTime = lastpostTime;
    }

    public String getLastpostUsername() {
        return lastpostUsername;
    }

    public void setLastpostUsername(String lastpostUsername) {
        this.lastpostUsername = lastpostUsername;
    }

    public int getIsBest() {
        return isBest;
    }

    public void setIsBest(int isBest) {
        this.isBest = isBest;
    }
}
