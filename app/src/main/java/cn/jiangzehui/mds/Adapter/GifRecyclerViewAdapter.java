package cn.jiangzehui.mds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.jiangzehui.mds.R;
import cn.jiangzehui.mds.model.Gif;
import cn.jiangzehui.mds.retrofit.HttpService;

/**
 * Created by quxianglin on 16/11/5.
 */
public class GifRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater inflaters;
    Context context;
    ArrayList<Gif> list;
    private OnItemClickLitener mOnItemClickLitener;
    private OnLoadListener mOnLoadListener;
    private boolean isFooterView = true;
    final int NOFOOT = 1;
    final int YESFOOT = 2;
    View footView;
    boolean gif_auto_bool = false;

    public GifRecyclerViewAdapter(Context context, ArrayList<Gif> list) {
        isFooterView = false;
        inflaters = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    public GifRecyclerViewAdapter(Context context, ArrayList<Gif> list, View footView) {
        isFooterView = true;
        inflaters = LayoutInflater.from(context);
        this.list = list;
        this.footView = footView;
        this.context = context;
    }

    public void setList(ArrayList<Gif> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    public int getListSize() {
        return this.list.size();
    }

    public void addList(ArrayList<Gif> lists) {
        if (lists.size() > 0) {
            for (Gif gif : lists) {
                this.list.add(gif);
            }
            notifyDataSetChanged();
        }

    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public void setOnLoadListener(OnLoadListener mOnLoadListener) {
        this.mOnLoadListener = mOnLoadListener;
    }


    public void setFooterView(boolean footerView) {
        isFooterView = footerView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView) {
            if (position == (list.size())) {
                return YESFOOT;
            } else {
                return NOFOOT;
            }
        } else {
            return NOFOOT;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case YESFOOT:
                holder = new MyHolder_foot(footView);
                break;

            case NOFOOT:
                holder = new MyHolder(inflaters.inflate(R.layout.item_recyclerview_gif, parent, false));
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyHolder) {
            MyHolder holder1 = (MyHolder) holder;

            holder1.setTitle(list.get(position).getTitle());

            holder1.setImg(list.get(position).getUrl());
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickLitener.onItemClick(view, position);
                    }
                });
            }
        }


    }

    @Override
    public int getItemCount() {
        return isFooterView ? list.size() + 1 : list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv;


        public MyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

        public void setTitle(String title) {
            if (null == tv_title) return;
            tv_title.setText(title + "\n");

        }


        public void setImg(String imgUrl) {


            if (null == iv) return;
            if (!imgUrl.contains("http")) {
                imgUrl = "http://www.zbjuran.com" + imgUrl;
                Log.i("imgUrl", imgUrl);
            }
            if (!gif_auto_bool) {
                final boolean[] bool = {true};
                final WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(iv);
                final ImageView target = imageViewWeakReference.get();
                if (target != null) {
                    Glide.with(context).load(imgUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(target);
                } else {
                    Glide.with(context).load(imgUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                }

                final String finalImgUrl = imgUrl;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bool[0]) {
                            bool[0] = false;
                            if (target != null) {
                                Glide.with(context).load(finalImgUrl).asGif().thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(target);
                            } else {
                                Glide.with(context).load(finalImgUrl).asGif().thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                            }
                        }

                    }
                });
            } else {
                Glide.with(context).load(imgUrl).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
            }

        }


    }

    class MyHolder_foot extends RecyclerView.ViewHolder {


        public MyHolder_foot(View itemView) {
            super(itemView);

        }
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    public interface OnLoadListener {
        void onLoad();
    }


}
