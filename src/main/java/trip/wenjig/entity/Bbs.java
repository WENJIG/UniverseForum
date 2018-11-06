package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "un_bbs")
public class Bbs implements Serializable {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bbsId\":")
                .append(bbsId);
        sb.append(",\"bbsName\":\"")
                .append(bbsName).append('\"');
        sb.append(",\"bbsIcon\":\"")
                .append(bbsIcon).append('\"');
        sb.append(",\"bbsSign\":\"")
                .append(bbsSign).append('\"');
        sb.append(",\"bbsWhiteList\":")
                .append(bbsWhiteList);
        sb.append(",\"bbsTopicNum\":")
                .append(bbsTopicNum);
        sb.append(",\"bbsFocusNum\":")
                .append(bbsFocusNum);
        sb.append('}');
        return sb.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bbs_id", insertable = false, updatable = false)
    private long bbsId;

    @Column(name = "bbs_name", nullable = false, unique = true, length = 50)
    private String bbsName;

    @Column(name = "bbs_icon", length = 100)
    private String bbsIcon;

    @Column(name = "bbs_sign", length = 100)
    private String bbsSign;

    @Column(name = "bbs_white_list")
    private ArrayList<User> bbsWhiteList;

    @Column(name = "bbs_topic_num")
    private int bbsTopicNum;

    @Column(name = "bbs_focus_num")
    private int bbsFocusNum;

    public long getBbsId() {
        return bbsId;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public String getBbsIcon() {
        return bbsIcon;
    }

    public void setBbsIcon(String bbsIcon) {
        this.bbsIcon = bbsIcon;
    }

    public String getBbsSign() {
        return bbsSign;
    }

    public void setBbsSign(String bbsSign) {
        this.bbsSign = bbsSign;
    }

    public ArrayList<User> getBbsWhiteList() {
        return bbsWhiteList;
    }

    public void setBbsWhiteList(ArrayList<User> bbsWhiteList) {
        this.bbsWhiteList = bbsWhiteList;
    }

    public int getBbsTopicNum() {
        return bbsTopicNum;
    }

    public void setBbsTopicNum(int bbsTopicNum) {
        this.bbsTopicNum = bbsTopicNum;
    }

    public int getBbsFocusNum() {
        return bbsFocusNum;
    }

    public void setBbsFocusNum(int bbsFocusNum) {
        this.bbsFocusNum = bbsFocusNum;
    }
}
