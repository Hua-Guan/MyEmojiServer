package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.bean.CloudEmojiBean;
import pri.guanhua.myemojiserver.dao.EmojiUploadDao;
import pri.guanhua.myemojiserver.entity.Emoji;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmojiUploadService {
    @Autowired
    EmojiUploadDao dao;

    public void setEmojisInfo(String account, String album, String emojiTitle, String md5){
        Emoji emoji = new Emoji();
        emoji.setEmojiUser(account);
        emoji.setEmojiAlbum(album);
        emoji.setEmojiTitle(emojiTitle);
        emoji.setEmojiMD5(md5);
        dao.save(emoji);
    }

    /**
     * 获取用户已上传表情包的路径
     * @param account
     * @return
     */
    public List<CloudEmojiBean> getCloudEmojiUrl(String account){
        List<Emoji> list = dao.findEmojiByEmojiUser(account);
        List<CloudEmojiBean> cloudEmojiBeans = new ArrayList<>();
        for (Emoji value : list) {
            CloudEmojiBean bean = new CloudEmojiBean();
            bean.setId(value.getId());
            String url = UserConst.URL
                    + UserConst.USER_CLOUD_EMOJI_BYTES
                    + "?"
                    + UserConst.USER_EMOJI_TITLE
                    + "="
                    + value.getEmojiMD5()
                    + "_"
                    + account
                    + ".jpg";
            bean.setEmojiUri(url);
            cloudEmojiBeans.add(bean);
        }
        return cloudEmojiBeans;
    }

    /**
     * 为安卓glide提供字节流
     */
    public void getCloudEmojiBytes(HttpServletResponse response,
                                   String emojiName) throws Exception{
        File file = new File(UserConst.PATH_CLOUD_EMOJI + emojiName);

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

}
