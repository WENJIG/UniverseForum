package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    @Query(value = "select u from User u where (u.userName=?1 or u.email=?1) and u.password=?2")
    User findByLogin(String userNameOrEmail, String password);

    @Query(value = "select u from User u where u.userName=?1 and u.email=?2")
    User findByUserNameAndUserEmail(String userName, String email);

    User findByUserId(long id);

    @Query(value = "select u.faceImagePath from User u where u.userName=?1")
    String findByUserFaceImagePath(String userName);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.password=?1 where u.userId=?2")
    int updateUserPassword(String password, long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.userLoginNumber=u.userLoginNumber+1 where u.userId=?1")
    int updateUserLoginNumber(long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.lastLoginDate=?1 where u.userId=?2")
    int updateUserLastLogin(String date, long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.faceImagePath=?1 where u.userId=?2")
    int updateUserHeadImagePath(String path, long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.signText=?1 where u.userId=?2")
    int updateUserSign(String sign, long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.sex=?1 where u.userId=?2")
    int updateUserSex(int sex, long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.topicNumber=?1 where u.userId=?2")
    int updateUserPostTopicNumber(int num, long id);

}
