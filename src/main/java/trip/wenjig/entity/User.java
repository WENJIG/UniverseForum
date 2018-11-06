package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "un_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId;

    @Column(name = "user_name", nullable = false, unique = true, length = 20)
    private String userName;

    @Column(name = "user_password", nullable = false, length = 50)
    private String password;

    @Column(name = "user_email", nullable = false, length = 100)
    private String email;

    @Column(name = "user_post", nullable = false)
    private int postNumber;

    @Column(name = "user_topic", nullable = false)
    private int topicNumber;

    @Column(name = "user_sign", length = 100)
    private String signText;

    @Column(name = "user_face", length = 250)
    private String faceImagePath;

    @Column(name = "user_sex", nullable = false)
    private int sex;

    @Column(name = "user_lv", nullable = false)
    private int level;

    @Column(name = "user_exp", nullable = false)
    private int exp;

    @Column(name = "user_title", length = 50)
    private String title;

    @Column(name = "user_info")
    private String userInfo;

    @Column(name = "user_idol")
    private ArrayList<String> idolUserName;

    @Column(name = "user_fans")
    private ArrayList<String> fansUserName;

    @Column(name = "user_bbs")
    private ArrayList<String> concernedBbsName;

    @Column(name = "join_date", nullable = false, insertable = false, updatable = false)
    private String joinDate;

    @Column(name = "last_login")
    private String lastLoginDate;

    @Column(name = "user_login_num")
    private int userLoginNumber;

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getSignText() {
        return signText;
    }

    public void setSignText(String signText) {
        this.signText = signText;
    }

    public String getFaceImagePath() {
        return faceImagePath;
    }

    public void setFaceImagePath(String faceImagePath) {
        this.faceImagePath = faceImagePath;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getIdolUserName() {
        return idolUserName;
    }

    public void setIdolUserName(ArrayList<String> idolUserName) {
        this.idolUserName = idolUserName;
    }

    public ArrayList<String> getFansUserName() {
        return fansUserName;
    }

    public void setFansUserName(ArrayList<String> fansUserName) {
        this.fansUserName = fansUserName;
    }

    public int getUserLoginNumber() {
        return userLoginNumber;
    }

    public void setUserLoginNumber(int userLoginNumber) {
        this.userLoginNumber = userLoginNumber;
    }

    public ArrayList<String> getConcernedBbsName() {
        return concernedBbsName;
    }

    public void setConcernedBbsName(ArrayList<String> concernedBbsName) {
        this.concernedBbsName = concernedBbsName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
