package com.myfstrong.myasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* 异步任务AsyncTask适用于简单的异步处理，不借助Handler和线程也可以实现。
*
* AsyncTask是一个抽象类，通常用于被继承，继承AsyncTask需要一下3个泛型参数：
* AsyncTask<Params, Progress, Result>
*     Params:启动任务执行输入参数的类型
*     Progress:后台任务完成的进度值类型
*     Result:后台任务执行完成后，返回结果的类型
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
