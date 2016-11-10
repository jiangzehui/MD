package cn.jiangzehui.mds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GifActivity extends AppCompatActivity {

    @InjectView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        ButterKnife.inject(this);
        String url = getIntent().getStringExtra("url");
        setImg(url);

    }

    public void setImg(String imgUrl) {


        if (!imgUrl.contains("http")) {
            imgUrl = "http://www.zbjuran.com" + imgUrl;
            Log.i("imgUrl", imgUrl);
        }
        if (imgUrl.contains("gif")) {

            final boolean[] bool = {true};
            final WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(iv);
            final ImageView target = imageViewWeakReference.get();
            if (target != null) {
                Glide.with(GifActivity.this).load(imgUrl).asGif().thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(target);
            } else {
                Glide.with(GifActivity.this).load(imgUrl).asGif().thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
            }


        } else {
            Glide.with(GifActivity.this).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(GifActivity.this).clearMemory();
    }
}
