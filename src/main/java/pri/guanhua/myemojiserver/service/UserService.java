package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    /**
     * 如果用户密码错误则返回“NO”，如果用户账号不存在则直接注册，返回“YES”。
     */
    public String YesOrNo(String userAccount, String userPassword){
        User user = userDao.findUserByuaccount(userAccount);
        if (user != null){
            if (user.getUpassword().equals(userPassword)){
                return "YES";
            }else {
                return "NO";
            }
        }else {
            User newUser = new User();
            newUser.setUaccount(userAccount);
            newUser.setUuri("22");
            //将密码加密为MD5
            String s = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
            newUser.setUpassword(s);
            userDao.save(newUser);
            return "YES";
        }
    }

    public void test(){
        User user = new User();
        user.setUaccount("99");
        String s = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        user.setUpassword(s);
        user.setUuri("2");
        userDao.save(user);
    }
}
