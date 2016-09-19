package com.imooc.liulinpeng.imocctantan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TanTanView ttvMain;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttvMain = (TanTanView) findViewById(R.id.ttv_main);
        ttvMain.fillData(new ArrayList<CardDataItem>() {
            {
                for (int i = 0; i < imagePaths.length; i++) {
                    add(new CardDataItem(imagePaths[i], names[i] + "", i));
                }
            }
        });

        TanTanView.CallBack callBack = new TanTanView.CallBack(){
            ;
            private static final String TAG = "MainActivity";
            @Override
            void leftExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            void rightExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            void onScroll(CardItemView changedView, int dx, int dy) {
                Log.d(TAG, "onScroll() called with: " + "dx = [" + dx + "], dy = [" + dy + "]");
                changedView.setheartAlpha(dx);
            }
        };
        ttvMain.addCallBack(callBack);
    }

    public void like(View view){
        ttvMain.like();
    }
    public void dislike(View view){
        ttvMain.dislike();
    }


    private String names[] = {"吴亦凡", "李易峰", "张杰", "陈晓", "冯绍峰", "炎亚纶",};


    public final String[] imagePaths = new String[]{
            "http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",}; // 24个图片资源名称



}
