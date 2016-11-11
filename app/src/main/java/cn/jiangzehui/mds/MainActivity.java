package cn.jiangzehui.mds;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.fragment.Gif_Fragment;
import cn.jiangzehui.mds.fragment.News_Fragment;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.util.T;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.pager)
    ViewPager pager;
    ArrayList<Fragment> list = new ArrayList<>();
    String[][] str_type = new String[][]{{"搞笑GIF", Ip.url_gif_dongtai, "gif"}, {"邪恶GIF", Ip.url_gif_xiegif, "gif"}, {"搞笑图片", Ip.url_gif_gaoxiao, "gif"}, {"头条", "top", "news"}, {"社会", "shehui", "news"}, {"国内", "guonei", "news"}, {"国际", "guoji", "news"}, {"娱乐", "yule", "news"}, {"体育", "tiyu", "news"}, {"军事", "junshi", "news"}, {"科技", "keji", "news"}, {"财经", "caijing", "news"}, {"时尚", "shishang", "news"}};
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.nv)
    NavigationView nv;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initView();
        //初始化数据
        for (int i = 0; i < str_type.length; i++) {
            if (str_type[i][2].contains("gif")) {
                list.add(Gif_Fragment.newInstance(str_type[i][1], i));
            } else {
                list.add(News_Fragment.newInstance(str_type[i][1]));
            }

        }
        adapter = new MyAdapter(getSupportFragmentManager());
        //pager.setOffscreenPageLimit(2);//设置保持叶面
        pager.setAdapter(adapter);


    }

    /**
     * 初始化各种控件
     */
    private void initView() {
        tab.setupWithViewPager(pager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("MD");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        setSupportActionBar(toolbar);//显示菜单栏

        //设置Toolbar和DrawerLayout实现动画和联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_news:
                if (!menuItem.isChecked()) {
                    T.show(MainActivity.this, "1");
                }

                break;
            case R.id.nav_video:
                if (!menuItem.isChecked()) {
                    T.show(MainActivity.this, "1");
                }
                break;
            case R.id.nav_gif:

                T.open(this, GifActivity.class);

                break;
            case R.id.nav_fuli:
                if (!menuItem.isChecked()) {
                    T.show(MainActivity.this, "1");
                }
                break;
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();


        return true;
    }


    /**
     * viewpager适配器，适配fragment
     */
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return str_type[position][0];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {//不销毁

        }
    }


}
