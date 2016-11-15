package cn.jiangzehui.mds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.media.view.VideoListLayout;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.model.Video;
import cn.jiangzehui.mds.retrofit.Api;
import cn.jiangzehui.mds.util.JsoupUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    @InjectView(R.id.layout)
    VideoListLayout layout;
    List<Video.DataBean.DataBeans> listData;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.inject(this);
        init();
    }





    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Api.getInstance(Ip.url_video).getService().Get_video("neihan/stream/mix/v1/").enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if(response.body()!=null){
                    listData = response.body().getData().getData();
                    layout.setListData(listData);
                }
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                Log.i("video",t.getMessage());
            }
        });

    }
}
