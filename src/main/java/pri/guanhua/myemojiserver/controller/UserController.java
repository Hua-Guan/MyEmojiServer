package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;
import pri.guanhua.myemojiserver.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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

    /**
     * 下载默认的头像
     * @param response
     * @throws Exception
     */
    @RequestMapping(UserConst.USER_DEFAULT_AVATAR)
    @ResponseBody
    public void getAvatar(HttpServletResponse response,
                          @RequestParam(UserConst.USER_ACCOUNT) String account) throws Exception{
        userService.getAvatar(response, account);
    }

    /**
     * 上传头像
     */
    @PostMapping(UserConst.USER_UPLOAD_AVATAR)
    @ResponseBody
    public void upLoadAvatar(@RequestPart MultipartFile file,
                             @RequestParam(UserConst.USER_ACCOUNT) String account) throws Exception{
        userService.uploadUserAvatar(file, account);
    }

    @GetMapping("test")
    public void test(){
        userService.test();
    }

}
