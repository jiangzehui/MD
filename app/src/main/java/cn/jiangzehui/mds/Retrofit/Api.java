package cn.jiangzehui.mds.retrofit;

import cn.jiangzehui.mds.model.Ip;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jiangzehui on 16/10/26.
 */
public class Api {
    private HttpService service;
    private static Api instances;

    private Converter.Factory factory = GsonConverterFactory.create();
    private String url = Ip.uri_news;


    public void setUrl(String url) {
        this.url = url;
    }

    public static Api getInstance() {
        if (instances == null) {
            instances = new Api();
        }

        return instances;
    }

    public static Api getInstance(String url) {

        instances = new Api(url);


        return instances;
    }


    private Api() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(factory).build();
        service = retrofit.create(HttpService.class);

    }

    private Api(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(factory).build();
        service = retrofit.create(HttpService.class);

    }

    public HttpService getService() {
        return service;
    }


}
