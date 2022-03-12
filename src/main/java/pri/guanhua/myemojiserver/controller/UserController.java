package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;
import pri.guanhua.myemojiserver.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @RequestMapping("user")
    public List<User> userList(){
        return userDao.findAll();
    }

    @GetMapping("getUser")
    public User getUserById(@RequestParam("id") String id){
        return userService.getUserById(Integer.valueOf(id));
    }

}
