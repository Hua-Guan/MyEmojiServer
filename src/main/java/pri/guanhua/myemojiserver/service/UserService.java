package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUserById(Integer id){
        return userDao.findUserById(id);
    }
}
