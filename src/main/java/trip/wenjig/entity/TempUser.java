package trip.wenjig.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "un_temp_register")
public class TempUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_id", insertable = false, updatable = false)
    private long id;

    @Column(name = "temp_code", nullable = false, length = 100)
    private String verificationCode;

    @Column(name = "temp_user_name", nullable = false, length = 20,unique = true)
    private String userName;

    @Column(name = "temp_user_password", nullable = false, length = 50)
    private String password;

    @Column(name = "temp_user_email", nullable = false, length = 100)
    private String mail;

    @Column(name = "temp_lose_efficacy_time", nullable = false)
    private String loseEfficacyTime;

    public long getId() {
        return id;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLoseEfficacyTime() {
        return loseEfficacyTime;
    }

    public void setLoseEfficacyTime(String loseEfficacyTime) {
        this.loseEfficacyTime = loseEfficacyTime;
    }
}
