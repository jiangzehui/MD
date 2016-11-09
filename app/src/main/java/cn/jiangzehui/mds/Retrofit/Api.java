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
    private static Api instances = new Api();

    private Converter.Factory factory = GsonConverterFactory.create();


    public static Api getInstance() {

        return instances;
    }


    private Api() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Ip.uri_news).addConverterFactory(factory).build();
        service = retrofit.create(HttpService.class);

    }

    public HttpService getService() {
        return service;
    }


}
