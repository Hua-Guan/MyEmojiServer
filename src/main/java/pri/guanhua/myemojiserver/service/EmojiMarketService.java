package pri.guanhua.myemojiserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.bean.EmojiMarketBean;
import pri.guanhua.myemojiserver.dao.EmojiMarketDao;
import pri.guanhua.myemojiserver.entity.EmojiMarket;
import pri.guanhua.myemojiserver.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmojiMarketService {

    @Autowired
    EmojiMarketDao dao;

    /**
     * 获取所有市场中表情包的uri
     * @return 返回json数据
     */
    public List<EmojiMarketBean> getAllEmojisUri(){
        List<EmojiMarketBean> list = new ArrayList<>();
        List<EmojiMarket> all = dao.findAll();
        for (EmojiMarket emoji : all){
            EmojiMarketBean bean = new EmojiMarketBean();
            bean.setId(emoji.getId());
            String url = UserConst.URL
                    + UserConst.USER_MARKET_EMOJI_BYTES
                    + "?"
                    + UserConst.USER_EMOJI_TITLE
                    + "="
                    + emoji.getEmojiUri();
            bean.setEmojiUri(url);
            list.add(bean);
        }
        return list;
    }

    /**
     * 输出字节流
     * @return 返回自己数组
     */
    public void getMarketEmojiBytes(HttpServletResponse response, String emojiName) throws Exception{
        File file = new File(UserConst.PATH_MARKET_EMOJI + emojiName);

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
