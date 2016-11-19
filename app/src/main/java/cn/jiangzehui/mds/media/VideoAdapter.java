package cn.jiangzehui.mds.media;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;


import cn.jiangzehui.mds.R;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.model.Video;
import cn.jiangzehui.mds.util.T;

/**
 * Author  wangchenchen
 * Description video列表adapter
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    Context context;

    private List<Video.Data.DataBean> list;

    public VideoAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoAdapter.VideoViewHolder holder = new VideoAdapter.VideoViewHolder(view);
        view.setTag(holder);
        return new VideoAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.VideoViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<Video.Data.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout videoLayout;
        private int position;
        private RelativeLayout showView;
        private TextView title, from,type;
        private ImageView iv;


        public VideoViewHolder(View itemView) {
            super(itemView);
            videoLayout = (FrameLayout) itemView.findViewById(R.id.layout_video);
            showView = (RelativeLayout) itemView.findViewById(R.id.showview);
            iv = (ImageView) itemView.findViewById(R.id.image_bg);
            title = (TextView) itemView.findViewById(R.id.title);
            from = (TextView) itemView.findViewById(R.id.from);
            type = (TextView) itemView.findViewById(R.id.type);
        }

        public void update(final int position) {
            this.position = position;
            if (list.get(position).getGroup() != null) {
                title.setText(list.get(position).getGroup().getText());
                type.setText(list.get(position).getGroup().getCategory_name());
                from.setText(list.get(position).getGroup().getPlay_count()+"次播放");
                if (list.get(position).getGroup().getLarge_cover().getUrl_list().get(0).getUrl().contains("http")) {
                    Glide.with(context).load(list.get(position).getGroup().getLarge_cover().getUrl_list().get(0).getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);

                } else {
                    Glide.with(context).load(Ip.url + list.get(position).getGroup().getLarge_cover().getUrl_list().get(0).getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);

                }
                showView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showView.setVisibility(View.GONE);
                        if (click != null)
                            click.onclick(position);
                    }
                });
            }

        }
    }

    private onClick click;

    public void setClick(onClick click) {
        this.click = click;
    }

    public static interface onClick {
        void onclick(int position);
    }
}
