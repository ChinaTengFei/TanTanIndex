package com.imooc.liulinpeng.imocctantan;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiuLinPeng on 2016/9/12.
 */
public class TanTanView extends ViewGroup {
    private static final int CARD_COUNT = 4;
    private static final int CARD_OFFSET = 30;
    private static final float CARD_SCALE = 0.08f;
    private List<CardItemView> releasedViewList = new ArrayList<>();
    private ViewDragHelper mViewDragHelper;

    private ArrayList<CardItemView> mViewList;
    private int mInitLeft;
    private int mInitTop;


    private int changedViewLeft;
    private int changedViewTop;

    private int mCurrentItemIndex = 1;
    private ArrayList<CardDataItem> cardItemViewList;

    public TanTanView(Context context) {
        this(context, null);
    }

    public TanTanView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TanTanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragCallBack());
    }

    private void initView() {
        mViewList = new ArrayList<>();
        LayoutInflater from = LayoutInflater.from(getContext());
        for (int i = 0; i < CARD_COUNT; i++) {
//            View view = from.inflate(R.layout.item, this, false);
//            TextView viewById = (TextView) view.findViewById(R.id.tv_name);
//            viewById.setText(i+"");
            CardItemView cardItemView = new CardItemView(getContext());
            addView(cardItemView, new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
            mViewList.add(cardItemView);
        }
        Collections.reverse(mViewList);
    }

    public void fillData(ArrayList<CardDataItem> cardItemViewList) {
        this.cardItemViewList = cardItemViewList;
        for (int i = 0; i < mViewList.size(); i++) {
            CardItemView cardItemView = mViewList.get(i);
            cardItemView.fillData(cardItemViewList.get(i));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        for (int i = 0; i < getChildCount(); i++) {
//            View childAt = getChildAt(i);
//            childAt.layout(l, t, r, b);
//            childAt.offsetTopAndBottom(i*CARD_OFFSET);
//        }
        int[] colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.GRAY};
        for (int i = 0; i < mViewList.size(); i++) {
            View childAt = mViewList.get(i);
            childAt.layout(l, t, r, b);
            childAt.setBackground(new ColorDrawable(colors[i]));
            float scaleX = 1 - CARD_SCALE * i;
            int offset = i * CARD_OFFSET;

            if (i == mViewList.size() - 1) {
                scaleX = 1 - CARD_SCALE * (i - 1);
                offset = (i - 1) * CARD_OFFSET;
            }

            childAt.offsetTopAndBottom(offset);

            childAt.setScaleX(scaleX);
            childAt.setScaleY(scaleX);

        }

        View view = mViewList.get(0);
        if (view != null) {
            mInitLeft = view.getLeft();
            mInitTop = view.getTop();

        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect rect = new Rect();
        mViewList.get(0).findViewById(R.id.ll_root).getHitRect(rect);
        boolean contains = rect.contains((int) event.getX(), (int) event.getY());
        mViewDragHelper.processTouchEvent(event);
        return contains;
    }

    public void like() {
        Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
        releasedViewList.add(mViewList.get(0));
        exitAnim(false);
    }


    public void dislike() {
        Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
        releasedViewList.add(mViewList.get(0));
        exitAnim(true);
    }

    class ViewDragCallBack extends ViewDragHelper.Callback {


        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return getChildAt(getChildCount() - 1) == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        private static final String TAG = "ViewDragCallBack";

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            changedViewLeft = left - mInitLeft;
            changedViewTop = top - mInitTop;
            float rate = changedViewLeft / 400f;

            if (rate <= -1.0) {

                rate = -1;
            }


            if (rate >= 1) {
                rate = 1;
            }

            int rote = (int) (rate * 30);
            changedView.setRotation(rote);

            for (int i = 1; i < mViewList.size() - 1; i++) {

                View view = mViewList.get(i);

                int offset = (int) (-Math.abs(rate) * CARD_OFFSET) - view.getTop() + CARD_OFFSET
                        * i;
                float scale = (1 - CARD_SCALE * i) + CARD_SCALE * Math.abs(rate);
                view.offsetTopAndBottom(offset);
                view.setScaleX(scale);
                view.setScaleY(scale);
                if (i == 1)
                    Log.i(TAG, "onViewPositionChanged: " + offset + "===" + scale + "===" + rate);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (changedViewLeft > 300 || xvel > 900) {
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                releasedViewList.add((CardItemView) releasedChild);
                exitAnim(false);
                return;
            } else if (changedViewLeft < -300 || xvel < -900) {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                releasedViewList.add((CardItemView) releasedChild);
                exitAnim(true);
                return;
            }

            mViewDragHelper.settleCapturedViewAt(0, 0);
            invalidate();

        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
//            if (state == ViewDragHelper.STATE_IDLE && releasedViewList.size() != 0) {
//
//                mViewDragHelper.getCapturedView().setRotation(0);
//                orderItem();
//                releasedViewList.clear();
//                loadItem();
//            }
        }


        private void orderItem() {
            for (int i = mViewList.size() - 1; i > 0; i--) {
                mViewList.get(i).bringToFront();
            }

            CardItemView view = mViewList.get(0);
            mViewList.remove(view);
            mViewList.add(view);
        }


    }

    private void loadItem() {
        CardItemView cardItemView = mViewList.get(mViewList.size() - 1);
        if (mCurrentItemIndex + 3 < cardItemViewList.size()) {
            CardDataItem cardDataItem = cardItemViewList.get(mCurrentItemIndex + 3);

            if (cardDataItem != null) {
                cardItemView.fillData(cardDataItem);
            }
            mCurrentItemIndex++;
        } else {
            cardItemView.setVisibility(View.GONE);
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(false)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private Object obj1 = new Object();

    private void exitAnim(boolean isLeft) {
        int finalLeft = 0;
        int finalTop = 0;

        CardItemView topView = mViewList.get(0);
        if (isLeft) {
            if (changedViewTop != 0) {
                finalTop = mViewDragHelper.getCapturedView().getWidth() * changedViewTop /
                        (-changedViewLeft) + changedViewTop;
            }
            finalLeft = -topView.getWidth();
        } else {
            if (changedViewTop != 0) {
                finalTop = topView.getWidth() * changedViewTop /
                        changedViewLeft;
            }
            finalLeft = getMeasuredWidth();
        }

        mViewDragHelper.smoothSlideViewTo(topView, (int) (finalLeft + getRoationOffset(topView
                        .getRotation(), topView.getWidth(), topView.getHeight()) - topView
                        .getWidth() / 2),
                finalTop);
        invalidate();

    }

    private double getRoationOffset(float angle, int width, int height) {
        //算出小三角形的角度
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;

        System.out.println("halfWidth" + halfWidth);
        System.out.println("halfHeight" + halfHeight);
        //小三角型的角度
        float i = halfWidth / halfHeight;

        System.out.println("tan" + i);
        double atan = Math.toDegrees(Math.atan(i));

        System.out.println("小三角形的角度" + atan);
        double sunAngle = atan + angle;
        double banjing = Math.sqrt(halfHeight * halfHeight + halfWidth * halfWidth);

        System.out.println("sunAngle" + sunAngle);
        double sin = Math.sin(Math.toRadians(sunAngle));

        System.out.println("sin" + sin);
        return sin * banjing;
    }


    protected interface FlingListener {
        void onCardExited();

        void leftExit(Object dataObject);

        void rightExit(Object dataObject);

        void onClick(Object dataObject);

        void onScroll(float scrollProgressPercent);

        void onMoveXY(float moveX, float moveY);
    }
}