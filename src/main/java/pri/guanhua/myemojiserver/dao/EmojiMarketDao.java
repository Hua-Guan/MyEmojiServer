package pri.guanhua.myemojiserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pri.guanhua.myemojiserver.entity.EmojiMarket;

@Repository
public interface EmojiMarketDao extends JpaRepository<EmojiMarket, Integer> {



}
