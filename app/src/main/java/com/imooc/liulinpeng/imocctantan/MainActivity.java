package com.imooc.liulinpeng.imocctantan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private String names[] = {"吴亦凡", "李易峰", "张杰", "陈晓", "冯绍峰", "炎亚纶",};
    String[] imagePath = {"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg"};

    private TanTanView ttvMain;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttvMain = ((TanTanView) findViewById(R.id.ttv_main));
        ArrayList<CardDataItem> cardDataItems = new ArrayList<>();

        for (int i = 0; i < imagePath.length; i++) {
            cardDataItems.add(new CardDataItem(imagePath[i], names[i], i));
        }

        ttvMain.fillData(cardDataItems);

        ttvMain.addCallBack(new TanTanView.CallBack() {
            @Override
            void leftExit(View exitView) {
                super.leftExit(exitView);
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            void rightExit(View exitView) {
                super.rightExit(exitView);
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void like(View view) {
        ttvMain.like();
    }

    public void dislike(View view) {
        ttvMain.dislike();
    }
}
