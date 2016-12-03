package cn.jiangzehui.mds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.jiangzehui.mds.R;
import cn.jiangzehui.mds.retrofit.HttpService;

/**
 * Created by jiangzehui on 16/11/5.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater inflaters;
    Context context;
    List<HttpService.Result.ResultBean.DataBean> list;
    private OnItemClickLitener mOnItemClickLitener;
    private OnLoadListener mOnLoadListener;
    private boolean isFooterView = true;
    final int NOFOOT = 1;
    final int YESFOOT = 2;
    View footView;

    public NewsRecyclerViewAdapter(Context context, List<HttpService.Result.ResultBean.DataBean> list) {
        isFooterView = false;
        inflaters = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    public NewsRecyclerViewAdapter(Context context, List<HttpService.Result.ResultBean.DataBean> list, View footView) {
        isFooterView = true;
        inflaters = LayoutInflater.from(context);
        this.list = list;
        this.footView = footView;
        this.context = context;
    }

    public void setList(List<HttpService.Result.ResultBean.DataBean> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    public int getListSize() {
        return this.list.size();
    }

    public void addList(List<HttpService.Result.ResultBean.DataBean> lists) {
        if (lists.size() > 0) {
            for (HttpService.Result.ResultBean.DataBean dataBean : lists) {
                this.list.add(dataBean);
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
                holder = new MyHolder(inflaters.inflate(R.layout.item_recyclerview_news, parent, false));
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyHolder) {
            MyHolder holder1 = (MyHolder) holder;

            holder1.setTitle(list.get(position).getTitle());
            holder1.setDate(list.get(position).getDate());
            holder1.setImg(list.get(position).getThumbnail_pic_s());
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
        TextView tv_title, tv_date;
        ImageView iv;

        public MyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

        public void setTitle(String title) {
            if (null == tv_title) return;
            tv_title.setText(title + "\n");

        }

        public void setDate(String date) {
            if (null == tv_date) return;
            tv_date.setText(date);
        }

        public void setImg(String imgUrl) {
            if (null == iv) return;
            Glide.with(context).load(imgUrl).centerCrop().placeholder(R.mipmap.ic_mr).crossFade().into(iv);
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
