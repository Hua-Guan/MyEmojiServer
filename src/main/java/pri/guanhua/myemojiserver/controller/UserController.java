package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;
import pri.guanhua.myemojiserver.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(UserConst.USER_LOGIN)
    public String canUserLogin(@RequestParam("uaccount") String uaccount,
                                   @RequestParam("upassword") String upassword){
        return userService.verifyAccount(uaccount, upassword);
    }

    @PostMapping(UserConst.USER_REGISTER)
    public String register(@RequestParam("uaccount") String uaccount,
                           @RequestParam("upassword") String upassword){
        return userService.registerAccount(uaccount, upassword);
    }

    @GetMapping("test")
    public void test(){
        userService.test();
    }

}
