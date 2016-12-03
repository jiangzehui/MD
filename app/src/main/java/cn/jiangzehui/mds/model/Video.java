package cn.jiangzehui.mds.model;

import java.util.List;

/**
 * Created by jiangzehui on 16/11/11.
 */
public class Video {


    private String message;


    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private boolean has_more;
        private String tip;


        private List<DataBean> data;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * 360p_video : {"width":480,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=360p&line=0&is_gif=0"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=360p&line=1&is_gif=0"}],"uri":"360p/507658d7a53144e0993fb58cd2089d3e","height":272}
             * mp4_url : http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=480p&line=0&is_gif=0.mp4
             * text : 爱心接力
             * 720p_video : {"width":480,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=720p&line=0&is_gif=0"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=720p&line=1&is_gif=0"}],"uri":"720p/507658d7a53144e0993fb58cd2089d3e","height":272}
             * digg_count : 194879
             * duration : 299.4
             * 480p_video : {"width":480,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=480p&line=0&is_gif=0"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=480p&line=1&is_gif=0"}],"uri":"480p/507658d7a53144e0993fb58cd2089d3e","height":272}
             * create_time : 1441012069
             * keywords :
             * id : 5439391456
             * favorite_count : 9643
             * go_detail_count : 132148
             * m3u8_url :
             * large_cover : {"url_list":[{"url":"http://p3.pstatp.com/large/7570/7776554894.webp"},{"url":"http://pb2.pstatp.com/large/7570/7776554894.webp"},{"url":"http://pb3.pstatp.com/large/7570/7776554894.webp"}],"uri":"large/7570/7776554894"}
             * user_favorite : 0
             * share_type : 1
             * title : 爱心接力
             * user : {"user_id":4019299698,"name":"我爱王司徒","followings":7,"user_verified":false,"ugc_count":196,"avatar_url":"http://p1.pstatp.com/thumb/10937/5500175313","followers":1197,"is_following":false,"is_pro_user":false}
             * is_can_share : 1
             * category_type : 1
             * share_url : http://m.neihanshequ.com/share/group/5439391456/?iid=6138644036&app=joke_essay
             * label : 1
             * content : 爱心接力
             * video_height : 272
             * comment_count : 4475
             * cover_image_uri : 7570/7776554894
             * id_str : 5439391456
             * media_type : 3
             * share_count : 23556
             * type : 2
             * category_id : 253
             * status : 112
             * has_comments : 0
             * publish_time :
             * user_bury : 0
             * activity : {}
             * status_desc : 热门投稿
             * dislike_reason : [{"type":1,"id":386,"title":"情感"},{"type":2,"id":253,"title":"吧:情感视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":4019299698,"title":"作者:我爱王司徒"}]
             * neihan_hot_start_time : 00-00-00
             * play_count : 2412267
             * user_repin : 0
             * quick_comment : false
             * medium_cover : {"url_list":[{"url":"http://p3.pstatp.com/w202/7570/7776554894.webp"},{"url":"http://pb2.pstatp.com/w202/7570/7776554894.webp"},{"url":"http://pb3.pstatp.com/w202/7570/7776554894.webp"}],"uri":"medium/7570/7776554894"}
             * neihan_hot_end_time : 00-00-00
             * user_digg : 0
             * video_width : 480
             * online_time : 1441012069
             * category_name : 情感视频
             * flash_url :
             * category_visible : true
             * bury_count : 3364
             * is_anonymous : false
             * repin_count : 9643
             * is_neihan_hot : false
             * uri : 507658d7a53144e0993fb58cd2089d3e
             * is_public_url : 1
             * has_hot_comments : 1
             * allow_dislike : true
             * origin_video : {"width":480,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=origin&line=0&is_gif=0"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=507658d7a53144e0993fb58cd2089d3e&quality=origin&line=1&is_gif=0"}],"uri":"origin/507658d7a53144e0993fb58cd2089d3e","height":272}
             * cover_image_url :
             * neihan_hot_link : {}
             * group_id : 5439391456
             * is_video : 1
             * display_type : 0
             */

            private GroupBean group;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public static class GroupBean {
                private String mp4_url;
                private String text;
                private double duration;
                private long create_time;

                /**
                 * url_list : [{"url":"http://p3.pstatp.com/large/7570/7776554894.webp"},{"url":"http://pb2.pstatp.com/large/7570/7776554894.webp"},{"url":"http://pb3.pstatp.com/large/7570/7776554894.webp"}]
                 * uri : large/7570/7776554894
                 */

                private LargeCoverBean large_cover;
                private int play_count;
                private String category_name;
                private int is_video;

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public String getMp4_url() {
                    return mp4_url;
                }

                public void setMp4_url(String mp4_url) {
                    this.mp4_url = mp4_url;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public double getDuration() {
                    return duration;
                }

                public void setDuration(double duration) {
                    this.duration = duration;
                }

                public LargeCoverBean getLarge_cover() {
                    return large_cover;
                }

                public void setLarge_cover(LargeCoverBean large_cover) {
                    this.large_cover = large_cover;
                }

                public int getPlay_count() {
                    return play_count;
                }

                public void setPlay_count(int play_count) {
                    this.play_count = play_count;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public int getIs_video() {
                    return is_video;
                }

                public void setIs_video(int is_video) {
                    this.is_video = is_video;
                }

                public static class LargeCoverBean {
                    private String uri;
                    /**
                     * url : http://p3.pstatp.com/large/7570/7776554894.webp
                     */

                    private List<UrlListBean> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<UrlListBean> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBean> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBean {
                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }
            }
        }
    }
}
