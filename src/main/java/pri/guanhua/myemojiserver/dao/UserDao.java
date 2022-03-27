package pri.guanhua.myemojiserver.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pri.guanhua.myemojiserver.entity.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByuaccount(String uaccount);

}
