package com.imooc.liulinpeng.imocctantan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * 卡片View项
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends LinearLayout {

    private final View item_left_indicator;
    private final View item_right_indicator;
    public ImageView imageView;
    private TextView userNameTv;
    private TextView imageNumTv;
    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    private <T> T getView(@IdRes int resId){
        return (T)findViewById(resId);
    }
    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.item, this);
        imageView = getView(R.id.iv_photo);
        userNameTv =getView(R.id.tv_name);
        imageNumTv =getView(R.id.tv_age);
        item_left_indicator =getView(R.id.item_left_indicator);
        item_right_indicator =getView(R.id.item_swipe_right_indicator);

    }
    
    public void fillData(CardDataItem itemData) {
        Glide.with(getContext()).load(itemData.imagePath).into(imageView);
        userNameTv.setText(itemData.userName);
        imageNumTv.setText(String.valueOf(itemData.imageNum));
    }

    private static final String TAG = "CardItemView";
    public void setheartAlpha(int dx){
        Log.i(TAG, "setheartAlpha: "+dx/600f+"==="+this);
        if(dx>0){
            item_left_indicator.setAlpha(dx/600f);
        }else{
            item_right_indicator.setAlpha(Math.abs(dx)/600f);
        }
    }

    public void reSet(){
        Log.i(TAG, "reSet: "+this);
        item_left_indicator.setAlpha(0f);
        item_right_indicator.setAlpha(0f);

        setRotation(0);
    }


}