package cn.jiangzehui.mds;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.fragment.News_Fragment;


public class MainActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        for (int i = 0; i < str_type.length; i++) {
            list.add(News_Fragment.newInstance(str_type[i][1]));
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("MD");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //pager.setOffscreenPageLimit(2);//设置保持叶面
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tab.setupWithViewPager(pager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);


        nv.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

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
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


}
