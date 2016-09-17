package com.imooc.liulinpeng.imocctantan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TanTanView ttvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    public void like(View view){
        ttvMain.like();
    }
    public void dislike(View view){
        ttvMain.dislike();
    }


    private String names[] = {"吴亦凡", "李易峰", "张杰", "陈晓", "冯绍峰", "炎亚纶", "胡歌", "林峰", "汪东城", "魏晨",
            "罗晋", "言承旭", "张若昀", "钟汉良", "陈楚生", "张翰", "乔振宇", "张睿", "邱胜翊", "陈学冬", "杜淳", "陈键锋",
            "焦恩俊", "韩庚", "邱泽", "柯震东", "毛若懿", "马天宇", "林子闳", "霍建华", "胡宇崴", "刘恩佑", "刘恺威", "陆毅",
            "唐禹哲", "李佳航", "吴建豪", "许绍洋", "戚迹", "赵鸿飞", "窦智孔", "陈浩民", "林奕勋", "周星驰", "何晟铭", "彭于晏",
            "黄海冰", "利昂霖", "翁瑞迪", "贾乃亮", "徐正曦", "俞灏明", "何炅", "蒋劲夫", "李维嘉", "成龙", "贺军翔", "黄晓明",
            "郭敬明", "武艺", "孙协志", "王传君", "陈德修", "吴尊", "刘俊纬", "魏斌", "吕杨", "李雨泽", "邱心志", "昊明", "郑元畅",
            "王睿", "余少群", "李威", "君君", "立威廉", "陈泽宇", "王栎鑫", "熊汝霖", "廖亦崟", "李诗琦", "薛之谦", "林更新",
            "郭晋安", "王煜", "孙小宝", "吴京", "成泰燊", "杨俊毅", "陈超尉", "甄子丹", "王新", "沈建宏", "李李仁", "黄鸿升",
            "邓宁", "廖俊杰", "张迪", "大张伟", "姚鑫", "李连杰", "盛超", "唐曾", "张译", "何润东"};


    public final String[] imagePaths = new String[]{
            "http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
            "http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
            "http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg"}; // 24个图片资源名称



}
