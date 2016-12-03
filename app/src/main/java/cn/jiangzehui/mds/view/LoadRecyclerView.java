package cn.jiangzehui.mds.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by jiangzehui on 16/12/1.
 */
public class LoadRecyclerView extends RecyclerView {

    private LinearLayoutManager manager;
    private boolean isScroll = false;
    private OnLoadingListener onLoadingListener;

    public LoadRecyclerView(Context context) {
        super(context);

    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


    }

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        this.onLoadingListener = onLoadingListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (manager == null) {
            if (getLayoutManager() instanceof LinearLayoutManager) {
                manager = (LinearLayoutManager) getLayoutManager();
            }
        }


        if (manager != null && onLoadingListener != null) {
            if (state == RecyclerView.SCROLL_STATE_IDLE && isScroll) {

                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                Log.i("Gif_Fragment", totalItemCount + "");
                Log.i("Gif_Fragment", lastVisibleItem + "");
                if (lastVisibleItem == (totalItemCount - 1)) {
                    Log.i("Gif_Fragment", "LoadMore");
                    onLoadingListener.onLoading();
                    isScroll = false;
                }
            }
        }

    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (dy > 0) {
            isScroll = true;
        } else {
            isScroll = false;
        }
    }


    public interface OnLoadingListener {
        void onLoading();
    }
}
