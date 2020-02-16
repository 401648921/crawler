package novels;
import tool.*;

public class Chapter {
    public String title = null;   //章节标题
    public String text = null;   //章节内容

    public Chapter() {
    }

    public Chapter(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
