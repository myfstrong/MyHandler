package com.myfstrong.myhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTxt_Dispaly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTxt_Dispaly = (TextView) findViewById(R.id.txt_display);


    }

    public void btn_star(View view) {
        handler.post(myRunnable);
    }

    public void btn_stop(View view) {
        handler.removeCallbacks(myRunnable);
    }

    //工作线程：将业务处理后，将处理情况通过消息对象传递给UI主线程
    Runnable myRunnable = new Runnable() {
        int i = 0;
        @Override
        public void run() {

            try {
                i++;
                Message message = new Message();
                message.arg1 = i;
                message.what = 0x14;

                Thread.sleep(50);
                handler.sendMessage(message);
                if (i == 100) {
                    handler.removeCallbacks(myRunnable);
                    //将进度条隐藏
                    mProgressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"加载完成！",Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    //通过Handler对UI主线程的界面做出改变：负责接收从工作现场中传递来的消息，并根据消息对UI界面做出相应改变
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x14 && msg.arg1 <= 100) {
                mProgressBar.setProgress(msg.arg1);
                mTxt_Dispaly.setText("当前进度为：" + msg.arg1 + "%");
                //在对UI界面做出改变后，再次通过Handler发送myRunnable工作线程，并在工作线程中做出相关处理
                handler.post(myRunnable);
            }
        }
    };
}
