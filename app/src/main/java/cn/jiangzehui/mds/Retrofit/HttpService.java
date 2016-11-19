package cn.jiangzehui.mds.retrofit;

import java.util.List;

import cn.jiangzehui.mds.model.Video;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jiangzehui on 16/10/17.
 */
public interface HttpService {


    @GET("index?key=9e05423f7ac6acf6d0dce3425c4ea9fe")
    Call<Result> Get_news(@Query("type") String type);


    @GET("neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-104&message_cursor=-1")
    Call<Video> Get_video();

    class Result {


        private String reason;
        private ResultBean result;
        private int error_code;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public static class ResultBean {
            private String stat;
            private List<DataBean> data;

            public String getStat() {
                return stat;
            }

            public void setStat(String stat) {
                this.stat = stat;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                private String title;
                private String date;
                private String category;
                private String author_name;
                private String thumbnail_pic_s;
                private String url;
                private String thumbnail_pic_s03;
                private String thumbnail_pic_s02;
                private String uniquekey;
                private String type;
                private String realtype;


                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getAuthor_name() {
                    return author_name;
                }

                public void setAuthor_name(String author_name) {
                    this.author_name = author_name;
                }

                public String getThumbnail_pic_s() {
                    return thumbnail_pic_s;
                }

                public void setThumbnail_pic_s(String thumbnail_pic_s) {
                    this.thumbnail_pic_s = thumbnail_pic_s;
                }

                public String getThumbnail_pic_s02() {
                    return thumbnail_pic_s02;
                }

                public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                    this.thumbnail_pic_s02 = thumbnail_pic_s02;
                }

                public String getThumbnail_pic_s03() {
                    return thumbnail_pic_s03;
                }

                public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                    this.thumbnail_pic_s03 = thumbnail_pic_s03;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUniquekey() {
                    return uniquekey;
                }

                public void setUniquekey(String uniquekey) {
                    this.uniquekey = uniquekey;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getRealtype() {
                    return realtype;
                }

                public void setRealtype(String realtype) {
                    this.realtype = realtype;
                }
            }
        }
    }


}
