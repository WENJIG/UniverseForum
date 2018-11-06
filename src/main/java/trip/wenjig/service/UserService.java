package trip.wenjig.service;

import org.springframework.stereotype.Service;
import trip.wenjig.entity.TempUser;
import trip.wenjig.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public interface UserService {

    void addUser(User user);

    void addTempUser(TempUser tempRegisterDomain);

    boolean updateUserPassword(String password,long id);

    boolean updateUserLoginNumber(long id);

    boolean updateUserLastLogin(String date, long id);

    boolean updateUserHeadImage(String path, long id);

    boolean updateUserSign(String sign, long id);

    boolean updateUserSex(int sex, long id);

    boolean updateUserPostTopicNumber(int num, long id);

    void deleteTempUser(TempUser tempUser);

    //void deleteTempUserByUserNameAndTempCode(String userName, String verificationCode);

    void deleteTempUserList(ArrayList<TempUser> tempUserArrayList);

    String findByUserFaceImagePath(String userName);

    HashMap<String,String> findByUserFaceImagePathMap(ArrayList<String> userNameList);

    TempUser findByTempUserNameAndTempCode(String userName, String verificationCode);

    TempUser findByTempUserName(String userName);

    ArrayList<TempUser> findAll();

    User findByLogin(String usernameOrEmail, String password);

    User findByUserNameAndUserEmail(String userName, String userEmail);

    User findByUserName(String userName);

    User findByUserId(long id);

}
