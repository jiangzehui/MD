package cn.jiangzehui.mds.media.view;

import java.io.Serializable;
import java.util.List;

import cn.jiangzehui.mds.model.Video;

/**
 * Author  wangchenchen
 * Description
 */
public class VideoListData implements Serializable {

    private List<Video> list;

    public List<Video> getList() {
        return list;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }
}
