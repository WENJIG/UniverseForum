package trip.wenjig.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trip.wenjig.repository.TempUserRepository;
import trip.wenjig.repository.UserRepository;
import trip.wenjig.entity.TempUser;
import trip.wenjig.entity.User;
import trip.wenjig.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TempUserRepository tempUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addTempUser(TempUser tempRegisterDomain) {
        tempUserRepository.save(tempRegisterDomain);
    }

    /*
    @Override
    public void deleteTempUserByUserNameAndTempCode(String userName, String verificationCode) {
        tempUserRepository.deleteByTempUserNameAndTempCode(userName, verificationCode);
    }
    */

    @Override
    public void deleteTempUserList(ArrayList<TempUser> tempUserArrayList) {
        tempUserRepository.deleteAll(tempUserArrayList);
    }

    @Override
    public String findByUserFaceImagePath(String userName) {
        return userRepository.findByUserFaceImagePath(userName);
    }

    @Override
    public HashMap<String, String> findByUserFaceImagePathMap(ArrayList<String> userNameList) {
        HashMap<String,String> map = new HashMap<>();
        for (int i = 0; i < userNameList.size(); i++) {
            map.put(userNameList.get(i),findByUserFaceImagePath(userNameList.get(i)));
        }
        return map;
    }

    @Override
    public boolean updateUserPassword(String password, long id) {
        return userRepository.updateUserPassword(password, id) > 0;
    }

    @Override
    public boolean updateUserLoginNumber(long id) {
        return userRepository.updateUserLoginNumber(id) > 0;
    }

    @Override
    public boolean updateUserLastLogin(String date, long id) {
        return userRepository.updateUserLastLogin(date, id) > 0;
    }

    @Override
    public boolean updateUserHeadImage(String path, long id) {
        return userRepository.updateUserHeadImagePath(path, id) > 0;
    }

    @Override
    public boolean updateUserSign(String sign, long id) {
        return userRepository.updateUserSign(sign, id) > 0;
    }

    @Override
    public boolean updateUserSex(int sex, long id) {
        return userRepository.updateUserSex(sex, id) > 0;
    }

    @Override
    public boolean updateUserPostTopicNumber(long id) {
        return userRepository.updateUserPostTopicNumber(id) > 0;
    }

    @Override
    public void deleteTempUser(TempUser tempUser) {
        tempUserRepository.delete(tempUser);
    }

    @Override
    public TempUser findByTempUserNameAndTempCode(String userName, String verificationCode) {
        return tempUserRepository.findByTempUserNameAndTempCode(userName, verificationCode);
    }

    @Override
    public TempUser findByTempUserName(String userName) {
        return tempUserRepository.findByUserName(userName);
    }

    @Override
    public ArrayList<TempUser> findAll() {
        return (ArrayList<TempUser>) tempUserRepository.findAll();
    }

    @Override
    public User findByLogin(String usernameOrEmail, String password) {
        return userRepository.findByLogin(usernameOrEmail, password);
    }

    @Override
    public User findByUserNameAndUserEmail(String userName, String userEmail) {
        return userRepository.findByUserNameAndUserEmail(userName, userEmail);
    }


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByUserId(long id) {
        return userRepository.findByUserId(id);
    }

}
