package pri.guanhua.myemojiserver.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "CLOUD_EMOJI")
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native",strategy = "native")
    int id;

    @Column(name = "EMOJI_TITLE")
    String emojiTitle;

    @Column(name = "EMOJI_MD5")
    String emojiMD5;

    @Column(name = "EMOJI_USER")
    String emojiUser;

    @Column(name = "EMOJI_ALBUM")
    String emojiAlbum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmojiTitle() {
        return emojiTitle;
    }

    public void setEmojiTitle(String emojiTitle) {
        this.emojiTitle = emojiTitle;
    }

    public String getEmojiMD5() {
        return emojiMD5;
    }

    public void setEmojiMD5(String emojiMD5) {
        this.emojiMD5 = emojiMD5;
    }

    public String getEmojiUser() {
        return emojiUser;
    }

    public void setEmojiUser(String emojiUser) {
        this.emojiUser = emojiUser;
    }

    public String getEmojiAlbum() {
        return emojiAlbum;
    }

    public void setEmojiAlbum(String emojiAlbum) {
        this.emojiAlbum = emojiAlbum;
    }
}
