package cn.jiangzehui.mds;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.adapter.PagerAdapter;
import cn.jiangzehui.mds.fragment.Gif_Fragment;
import cn.jiangzehui.mds.fragment.News_Fragment;

import cn.jiangzehui.mds.util.T;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.pager)
    ViewPager pager;
    ArrayList<Fragment> list = new ArrayList<>();
    String[][] str_type = new String[][]{{"头条", "top"}, {"社会", "shehui"}, {"国内", "guonei"}, {"国际", "guoji"}, {"娱乐", "yule"}, {"体育", "tiyu"}, {"军事", "junshi"}, {"科技", "keji"}, {"财经", "caijing"}, {"时尚", "shishang"}};
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.nv)
    NavigationView nv;
    PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();


    }

    /**
     * 初始化
     */
    private void init() {
        tab.setupWithViewPager(pager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //设置Toolbar和DrawerLayout实现动画和联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nv.setNavigationItemSelectedListener(this);
        //初始化数据
        for (int i = 0; i < str_type.length; i++) {
            list.add(News_Fragment.newInstance(str_type[i][1]));
        }
        adapter = new PagerAdapter(getSupportFragmentManager(), list, str_type);
        //pager.setOffscreenPageLimit(2);//设置保持叶面
        pager.setAdapter(adapter);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_video:
                T.open(this, VideoActivity.class, true);
                break;
            case R.id.nav_gif:

                T.open(this, GifActivity.class, true);

                break;

        }

        drawerLayout.closeDrawers();


        return true;
    }


    long newTime;

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(nv)){
            drawerLayout.closeDrawers();
            return;
        }


        if (System.currentTimeMillis() - newTime > 2000) {
            newTime = System.currentTimeMillis();
            Snackbar snackbar = Snackbar.make(drawerLayout, "再按一次返回键退出程序", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
            //Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }


}
