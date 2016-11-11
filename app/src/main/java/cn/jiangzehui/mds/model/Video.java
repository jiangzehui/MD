package cn.jiangzehui.mds.model;

/**
 * Created by quxianglin on 16/11/11.
 */
public class Video {
    private String title;
    private String img;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video(String title, String img, String url) {
        this.title = title;
        this.img = img;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
