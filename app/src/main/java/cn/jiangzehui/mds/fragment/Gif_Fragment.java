package cn.jiangzehui.mds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.ShowPicActivity;
import cn.jiangzehui.mds.R;
import cn.jiangzehui.mds.adapter.GifRecyclerViewAdapter;
import cn.jiangzehui.mds.model.Gif;
import cn.jiangzehui.mds.util.JsoupUtil;
import cn.jiangzehui.mds.util.T;
import cn.jiangzehui.mds.view.LoadRecyclerView;

/**
 * Created by jiangzehui on 16/11/9.
 */
public class Gif_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadingListener {

    View view;
    @InjectView(R.id.rv)
    LoadRecyclerView rv;
    @InjectView(R.id.fresh)
    SwipeRefreshLayout fresh;
    String url;
    ArrayList<Gif> list = new ArrayList<>();
    GifRecyclerViewAdapter adapter;
    View footView;
    TextView tv;
    ProgressBar pb;

    int index = 0;
    int type;

    public static Gif_Fragment newInstance(String url, int type) {
        Gif_Fragment gifFragment = new Gif_Fragment();
        Bundle bd = new Bundle();
        bd.putString("url", url);
        bd.putInt("type", type);
        gifFragment.setArguments(bd);
        return gifFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_gif, container, false);
        }
        ButterKnife.inject(this, view);
        fresh.setColorSchemeResources(R.color.main, android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
        fresh.setOnRefreshListener(this);

        Bundle bundle = getArguments();
        url = bundle.getString("url");
        type = bundle.getInt("type");
        getData(url);
        return view;
    }


    private void getData(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                list = JsoupUtil.getGif(url, type);
                if (list.size() > 0) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUi();
                            }
                        });
                    }


                }

            }
        }.start();


    }

    private void updateUi() {
        if (fresh != null) {
            fresh.setRefreshing(false);
        }


        if (adapter == null) {
            footView = LayoutInflater.from(getActivity()).inflate(R.layout.item_footview, null);
            footView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv = (TextView) footView.findViewById(R.id.tv);
            pb = (ProgressBar) footView.findViewById(R.id.pb);
            adapter = new GifRecyclerViewAdapter(getActivity(), list, footView);
            adapter.setOnItemClickLitener(new GifRecyclerViewAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    T.open(getActivity(), ShowPicActivity.class, "url", adapter.list.get(position).getUrl());
                }
            });
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv.setOnLoadingListener(this);
                rv.setAdapter(adapter);
            }

        } else {
            adapter.setList(list);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        Glide.get(getActivity()).clearMemory();
    }

    @Override
    public void onRefresh() {
        Glide.get(getActivity()).clearMemory();
        getData(url);
    }

    @Override
    public void onLoading() {
        Log.i("giffragment", "onLoading");
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (index < JsoupUtil.getListSize(type)) {
                    index++;
                    list = new ArrayList<Gif>();
                    list = JsoupUtil.getGif(url + JsoupUtil.getListItem(index, type), type);
                    if (list.size() > 0) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addList(list);
                            }
                        });
                    }
                } else {
                    pb.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                }

            }
        }.start();
    }


    @Override
    public void onPause() {
        super.onPause();
        Glide.get(getActivity()).clearMemory();

    }


}
