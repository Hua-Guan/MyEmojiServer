package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.guanhua.myemojiserver.dao.EmojiUploadDao;
import pri.guanhua.myemojiserver.entity.Emoji;

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

}
