package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.TempUser;

import javax.transaction.Transactional;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {

    TempUser findByUserName(String userName);

    @Query(value = "select t from TempUser t where t.userName=?1 and t.verificationCode=?2")
    TempUser findByTempUserNameAndTempCode(String userName, String VerificationCode);

    @Modifying
    @Transactional
    @Query(value = "delete from TempUser t where t.userName=?1 and t.verificationCode=?2")
    TempUser deleteByTempUserNameAndTempCode(String userName, String VerificationCode);

}
