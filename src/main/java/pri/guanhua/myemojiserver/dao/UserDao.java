package pri.guanhua.myemojiserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pri.guanhua.myemojiserver.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
}
