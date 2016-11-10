package cn.jiangzehui.mds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
        Glide.with(this).load("http://www.zbjuran.com/uploads/allimg/161029/2-161029162PGI.gif").asBitmap().into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(GifActivity.this).load("http://www.zbjuran.com/uploads/allimg/161029/2-161029162PGI.gif").asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(iv);
            }
        });

    }
}
