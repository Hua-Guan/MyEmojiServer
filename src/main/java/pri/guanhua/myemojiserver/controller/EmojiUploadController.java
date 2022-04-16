package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.service.EmojiUploadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class EmojiUploadController {

    @Autowired
    EmojiUploadService emojiUploadService;

    @PostMapping(UserConst.USER_UPLOAD_EMOJIS)
    @ResponseBody
    public void uploadEmoji(@RequestPart MultipartFile file) throws Exception{
        String filePath = "D:\\AndroidProject\\Emojis\\" + file.getOriginalFilename() + ".jpg";
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

    @PostMapping(UserConst.USER_UPDATE_EMOJI)
    public void updateEmoji(@RequestParam(UserConst.USER_ACCOUNT) String uaccount,
                            @RequestParam(UserConst.USER_ALBUM) String album,
                            @RequestParam(UserConst.USER_EMOJI_TITLE) String emojiTitle,
                            @RequestParam(UserConst.USER_EMOJI_MD5) String md5){
        emojiUploadService.setEmojisInfo(uaccount, album, emojiTitle, md5);
    }

}
