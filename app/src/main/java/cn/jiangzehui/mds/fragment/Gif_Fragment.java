package cn.jiangzehui.mds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jiangzehui.mds.R;
import cn.jiangzehui.mds.WebActivity;
import cn.jiangzehui.mds.adapter.GifRecyclerViewAdapter;
import cn.jiangzehui.mds.adapter.NewsRecyclerViewAdapter;
import cn.jiangzehui.mds.model.Gif;
import cn.jiangzehui.mds.retrofit.Api;
import cn.jiangzehui.mds.retrofit.HttpService;
import cn.jiangzehui.mds.util.JsoupUtil;
import cn.jiangzehui.mds.util.T;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quxianglin on 16/11/9.
 */
public class Gif_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    @InjectView(R.id.rv)
    RecyclerView rv;
    @InjectView(R.id.fresh)
    SwipeRefreshLayout fresh;
    String url;
    ArrayList<Gif> list = new ArrayList<>();
    GifRecyclerViewAdapter adapter;
    View footView;
    TextView tv;
    ProgressBar pb;

    public static Gif_Fragment newInstance(String url) {
        Gif_Fragment gifFragment = new Gif_Fragment();
        Bundle bd = new Bundle();
        bd.putString("url", url);
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
        getData(url);
        return view;
    }


    private void getData(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                list = JsoupUtil.getGif(url);
                if (list.size() > 0) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUi();
                        }
                    });

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
            adapter = new GifRecyclerViewAdapter(getActivity(), list);
//                adapter.setOnItemClickLitener(new NewsRecyclerViewAdapter.OnItemClickLitener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        T.open(getActivity(), WebActivity.class, "url", result.getResult().getData().get(position).getUrl());
//                    }
//                });
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));


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
    }

    @Override
    public void onRefresh() {
        getData(url);
    }
}
