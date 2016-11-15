package cn.jiangzehui.mds;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.adapter.PagerAdapter;
import cn.jiangzehui.mds.fragment.Gif_Fragment;
import cn.jiangzehui.mds.model.Ip;

public class GifActivity extends AppCompatActivity {

    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.pager)
    ViewPager pager;
    String str[][] = new String[][]{{"搞笑GIF", Ip.url_gif_dongtai}, {"邪恶GIF", Ip.url_gif_xiegif}, {"搞笑图片", Ip.url_gif_gaoxiao}};
    ArrayList<Fragment> list = new ArrayList<>();
    PagerAdapter adapter;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
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

        tab.setupWithViewPager(pager);
        tab.setTabMode(TabLayout.MODE_FIXED);
        //初始化数据
        for (int i = 0; i < str.length; i++) {

            list.add(Gif_Fragment.newInstance(str[i][1], i));


        }
        adapter = new PagerAdapter(getSupportFragmentManager(), list, str);
        //pager.setOffscreenPageLimit(2);//设置保持叶面
        pager.setAdapter(adapter);
    }


}
