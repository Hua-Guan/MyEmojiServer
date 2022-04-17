package pri.guanhua.myemojiserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pri.guanhua.myemojiserver.entity.Emoji;

import java.util.List;

@Repository
public interface EmojiUploadDao extends JpaRepository<Emoji, Integer> {

    int countByEmojiUser(String emojiUser);

    List<Emoji> findEmojiByEmojiUser(String emojiUser);

}
