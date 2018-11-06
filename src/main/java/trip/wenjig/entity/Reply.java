package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "un_reply")
public class Reply implements Serializable {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"replyId\":")
                .append(replyId);
        sb.append(",\"context\":\"")
                .append(context).append('\"');
        sb.append(",\"replyUsername\":\"")
                .append(replyUsername).append('\"');
        sb.append(",\"replyToUsername\":\"")
                .append(replyToUsername).append('\"');
        sb.append(",\"replyTime\":\"")
                .append(replyTime).append('\"');
        sb.append(",\"isfloorId\":")
                .append(isfloorId);
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", insertable = false, updatable = false)
    private long replyId;

    @Column(name = "context", nullable = false, length = 500)
    private String context;

    @Column(name = "reply_username", nullable = false, length = 20)
    private String replyUsername;

    @Column(name = "reply_tousername", nullable = false, length = 20)
    private String replyToUsername;

    @Column(name = "reply_time", nullable = false, insertable = false, updatable = false)
    private String replyTime;

    @Column(name = "isfloor_id", nullable = false)
    private long isfloorId;

    public long getReplyId() {
        return replyId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    public String getReplyToUsername() {
        return replyToUsername;
    }

    public void setReplyToUsername(String replyToUsername) {
        this.replyToUsername = replyToUsername;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public long getIsfloorId() {
        return isfloorId;
    }

    public void setIsfloorId(long isfloorId) {
        this.isfloorId = isfloorId;
    }
}
