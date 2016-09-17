package com.imooc.liulinpeng.imocctantan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
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
    }
    
    public void fillData(CardDataItem itemData) {
        Glide.with(getContext()).load(itemData.imagePath).into(imageView);
        userNameTv.setText(itemData.userName);
        imageNumTv.setText(String.valueOf(itemData.imageNum));
    }
}