package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.bean.CloudEmojiBean;
import pri.guanhua.myemojiserver.service.EmojiUploadService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
public class EmojiUploadController {

    @Autowired
    EmojiUploadService emojiUploadService;

    /**
     * 上传表情包
     * @param file 表情包file
     * @throws Exception 可能的异常
     */
    @RequestMapping(UserConst.USER_UPLOAD_EMOJIS)
    @ResponseBody
    public void uploadEmoji(@RequestPart MultipartFile file,
                            @RequestParam(UserConst.USER_ACCOUNT) String account,
                            @RequestParam(UserConst.USER_EMOJI_MD5) String md5) throws Exception{
        String filePath = UserConst.PATH_CLOUD_EMOJI + md5 + "_" + account + ".jpg";
        File dest = new File(filePath);
        OutputStream outputStream = new FileOutputStream(dest);
        InputStream inputStream = file.getInputStream();

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 把上传的表情包的数据更新在数据库中
     * @param uaccount 用户名
     * @param album 表情包所属的收藏夹
     * @param emojiTitle 表情包的名称
     * @param md5 表情包的md5
     */
    @PostMapping(UserConst.USER_UPDATE_EMOJI)
    public void updateEmoji(@RequestParam(UserConst.USER_ACCOUNT) String uaccount,
                            @RequestParam(UserConst.USER_ALBUM) String album,
                            @RequestParam(UserConst.USER_EMOJI_TITLE) String emojiTitle,
                            @RequestParam(UserConst.USER_EMOJI_MD5) String md5){
        emojiUploadService.setEmojisInfo(uaccount, album, emojiTitle, md5);
    }

    /**
     * 获取用户已上传表情包的路径
     * @param account
     * @return
     */
    @PostMapping(UserConst.USER_CLOUD_EMOJI)
    @ResponseBody
    public List<CloudEmojiBean> getCloudEmojiUrl(@RequestParam(UserConst.USER_ACCOUNT) String account){
       return emojiUploadService.getCloudEmojiUrl(account);
    }

    /**
     * 为安卓的glide获取字节流
     */
    @GetMapping(UserConst.USER_CLOUD_EMOJI_BYTES)
    public void getCloudEmojiBytes(HttpServletResponse response,
                                   @RequestParam(UserConst.USER_EMOJI_TITLE) String emojiName) throws Exception{
        emojiUploadService.getCloudEmojiBytes(response, emojiName);
    }

}
