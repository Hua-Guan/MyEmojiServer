package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据用户的用户名和密码判断用户是否能登入
     * @param uaccount 用户名
     * @param upassword 密码
     * @return 返回登入状态
     */
    @PostMapping(UserConst.USER_LOGIN)
    public String canUserLogin(@RequestParam("uaccount") String uaccount,
                                   @RequestParam("upassword") String upassword){
        return userService.verifyAccount(uaccount, upassword);
    }

    /**
     * 注册
     * @param uaccount 用户名
     * @param upassword 密码
     * @return 返回注册状态
     */
    @PostMapping(UserConst.USER_REGISTER)
    public String register(@RequestParam("uaccount") String uaccount,
                           @RequestParam("upassword") String upassword){
        return userService.registerAccount(uaccount, upassword);
    }

    /**
     * 下载默认的头像
     * @param response 响应头，用于返回字节流
     * @throws Exception 异常处理
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

    /**
     * 获取用户上传的表情包总数
     * @return 返回表情包总数
     */
    @PostMapping(UserConst.USER_EMOJIS_COUNT)
    @ResponseBody
    public String getCloudEmojisCount(@RequestParam(UserConst.USER_ACCOUNT) String account){
        return userService.getCloudEmojisCount(account);
    }

    @GetMapping("test")
    public void test(){
        userService.test();
    }

}
