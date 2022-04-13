package pri.guanhua.myemojiserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pri.guanhua.myemojiserver.entity.Emoji;

@Repository
public interface EmojiUploadDao extends JpaRepository<Emoji, Integer> {

}
