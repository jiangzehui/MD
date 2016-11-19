package cn.jiangzehui.mds;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.media.view.VideoListLayout;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.model.Video;
import cn.jiangzehui.mds.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.layout)
    VideoListLayout layout;
    List<Video.Data.DataBean> listData;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.fresh)
    SwipeRefreshLayout fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.inject(this);
        init();
    }


    private void init() {
        fresh.setColorSchemeResources(R.color.main, android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
        fresh.setOnRefreshListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();

    }

    private void getData() {

        Api.getInstance(Ip.url_video).getService().Get_video().enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (fresh != null) {
                    fresh.setRefreshing(false);
                }
                if (response.body() != null) {
                    listData = response.body().getData().getData();
                    for (int i = 0; i < listData.size(); i++) {
                        if(listData.get(i).getType() != 1){//过滤掉不是视频的数据
                            listData.remove(i);
                        }
                    }
                    layout.setListData(listData);
                }
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                //Log.i("video", t.getMessage());
                if (fresh != null) {
                    fresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            toolbar.setVisibility(View.GONE);
        }else{
            toolbar.setVisibility(View.VISIBLE);
        }


    }
}
