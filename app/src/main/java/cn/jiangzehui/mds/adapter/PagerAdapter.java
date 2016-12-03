package cn.jiangzehui.mds.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by jiangzehui on 16/11/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[][] str_type;

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[][] str_type) {
        super(fm);
        this.list = list;
        this.str_type = str_type;
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
