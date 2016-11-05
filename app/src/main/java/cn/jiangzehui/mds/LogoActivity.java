package cn.jiangzehui.mds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jiangzehui.mds.util.T;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new Thread(){
            @Override
            public void run() {
                T.open(LogoActivity.this,MainActivity.class);
                finish();
            }
        }.start();
    }
}
