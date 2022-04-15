package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.dao.UserDao;
import pri.guanhua.myemojiserver.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    /**
     * 如果用户密码错误则返回“USER_PASSWORD_MISTAKE”，
     * 如果用户账号不存在则返回“USER_ACCOUNT_NOT_EXIST”，
     * 如果账号和密码都正确则返回“USER_LOGIN_SUCCESS”。
     */
    public String verifyAccount(String userAccount, String userPassword){
        //将密码加密为MD5
        String md5Password = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        User user = userDao.findUserByuaccount(userAccount);
        if (user != null){
            if (user.getUpassword().equals(md5Password)){
                return UserConst.USER_LOGIN_SUCCESS;
            }else {
                return UserConst.USER_PASSWORD_MISTAKE;
            }
        }else {
            return UserConst.USER_ACCOUNT_NOT_EXIST;
        }
    }

    public String registerAccount(String userAccount, String userPassword){
        User user = userDao.findUserByuaccount(userAccount);
        if (user != null){
            return UserConst.USER_REGISTER_ALREADY_EXIST;
        }else {
            User newUser = new User();
            newUser.setUaccount(userAccount);
            newUser.setUuri("classpath:static/paimeng.jpg");
            //将密码加密为MD5
            String s = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
            newUser.setUpassword(s);
            userDao.save(newUser);
            return UserConst.USER_REGISTER_SUCCESS;
        }
    }

    /**
     * 获取头像
     */
    public void getAvatar(HttpServletResponse response, String account) throws Exception{
        File file;
        if ("classpath:static/paimeng.jpg".equals(account)) {
            file = ResourceUtils.getFile("classpath:static/paimeng.jpg");
        }else {
            file = new File(UserConst.URL_AVATAR + "user_avatar_" + account + ".jpg");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment;filename=paimeng.jpg");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[1024];
        OutputStream os = response.getOutputStream();
        int i = 0 ;
        while ((i = bis.read(buff)) != -1){
            os.write(buff, 0, i);
            os.flush();
        }
        os.close();
    }

    /**
     * 上传头像并更新数据库中头像的路径
     * @param file
     * @param account
     */
    public void uploadUserAvatar(MultipartFile file, String account) throws Exception{
        //上传头像
        String filePath = UserConst.URL_AVATAR + "user_avatar_" + account + ".jpg";
        File dest = new File(filePath);
        if (dest.exists()){
            dest.delete();
            dest = new File(filePath);
        }
        Files.copy(file.getInputStream(), dest.toPath());
        //更新数据库
        User user = userDao.findUserByuaccount(account);
        user.setUuri(filePath);
        userDao.save(user);
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
