package pri.guanhua.myemojiserver.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "emoji_market")
public class EmojiMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native",strategy = "native")
    int id;

    @Column(name = "EMOJI_URI")
    String emojiUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmojiUri() {
        return emojiUri;
    }

    public void setEmojiUri(String emojiUri) {
        this.emojiUri = emojiUri;
    }
}
