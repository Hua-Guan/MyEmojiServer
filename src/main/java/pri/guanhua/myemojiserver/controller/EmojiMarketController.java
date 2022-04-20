package pri.guanhua.myemojiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pri.guanhua.myemojiserver.UserConst;
import pri.guanhua.myemojiserver.bean.EmojiMarketBean;
import pri.guanhua.myemojiserver.entity.EmojiMarket;
import pri.guanhua.myemojiserver.service.EmojiMarketService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class EmojiMarketController {

    @Autowired
    EmojiMarketService service;

    /**
     * 获取表情包市场中的表情包所有的uri
     * @return 返回json数据
     */
    @RequestMapping(UserConst.USER_EMOJI_MARKET)
    public List<EmojiMarketBean> getAllEmojisUri(){
        return service.getAllEmojisUri();
    }

    /**
     * 为安卓的glide获取字节流
     */
    @RequestMapping(UserConst.USER_MARKET_EMOJI_BYTES)
    public void getMarketEmojiBytes(HttpServletResponse response,
                                    @RequestParam(UserConst.USER_EMOJI_TITLE) String emojiName) throws Exception{
        service.getMarketEmojiBytes(response, emojiName);
    }

}
