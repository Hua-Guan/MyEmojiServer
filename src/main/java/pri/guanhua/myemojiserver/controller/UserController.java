package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;
    @RequestMapping("user")
    public List<User> userList(){
        return userDao.findAll();
    }

}
