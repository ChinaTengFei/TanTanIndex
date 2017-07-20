package com.imooc.liulinpeng.imocctantan;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
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
    private static final int CARD_OFFSET = 60;
    private static final float CARD_SCALE = 0.08f;
    private List<CardItemView> releasedViewList = new ArrayList<>();
    private ViewDragHelper mViewDragHelper;

    private ArrayList<CardItemView> mViewList;
    private int mInitLeft;
    private int mInitTop;



    private int mCurrentItemIndex = 1;
    private ArrayList<CardDataItem> cardItemDataList;
    private CallBack mCallBack;

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

    public void fillData(ArrayList<CardDataItem> cardItemDataList) {
        this.cardItemDataList = cardItemDataList;
        for (int i = 0; i < mViewList.size(); i++) {
            CardItemView cardItemView = mViewList.get(i);
            cardItemView.fillData(cardItemDataList.get(i));
        }
    }
    private void loadItem() {
        CardItemView cardItemView = mViewList.get(0);
        if (mCurrentItemIndex + 3 < cardItemDataList.size()) {
            CardDataItem cardDataItem = cardItemDataList.get(mCurrentItemIndex + 3);
            Log.i(TAG, "loadItem: "+(mCurrentItemIndex + 3));
            if (cardDataItem != null) {
                cardItemView.fillData(cardDataItem);
            }
            mCurrentItemIndex++;
        } else {
            cardItemView.setVisibility(View.GONE);
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
        for (int i = 0; i < mViewList.size(); i++) {
            View childAt = mViewList.get(i);
            childAt.layout(l, t, r, b);
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

    private static final String TAG = "TanTanView";
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        orderItem();
        Log.i(TAG, "onInterceptTouchEvent: ");
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
        exitAnim(false,0,0);
    }


    public void dislike() {
        Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
        releasedViewList.add(mViewList.get(0));
        exitAnim(true,0,0);
    }

    class ViewDragCallBack extends ViewDragHelper.Callback {


        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mViewList.get(0) == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (mCallBack != null) {
                mCallBack.onScroll((CardItemView) child, child.getLeft(), child.getTop());
            }
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


            int changedViewLeft = left - mInitLeft;
            int changedViewTop = top - mInitTop;


            float rate = changedViewLeft / 600f;

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
                int visibility = view.getVisibility();
                if (visibility!=View.VISIBLE) {
                    Log.i(TAG, "onViewPositionChanged: asds");
                    continue;
                }

                //
                int offset = (int) (-Math.abs(rate) * CARD_OFFSET) - view.getTop() + CARD_OFFSET * i;

                float scale = (1 - CARD_SCALE * i) + CARD_SCALE * Math.abs(rate);
                view.offsetTopAndBottom(offset);
                view.setScaleX(scale);
                view.setScaleY(scale);
                    Log.i(TAG, "onViewPositionChanged: " + offset + "===" + scale + "===" + rate);
            }


            ((CardItemView) changedView).setheartAlpha(left);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            int releasedChildLeft = releasedChild.getLeft();
            int releasedChildTop = releasedChild.getTop();
            Log.i(TAG, "onViewReleased: "+releasedChildLeft+"===xvel"+xvel);
            if (releasedChildLeft > 300 || xvel > 900) {
                if (mCallBack != null) {
                    mCallBack.rightExit(releasedChild);
                }
                releasedViewList.add((CardItemView) releasedChild);
                exitAnim(false,releasedChildLeft,releasedChildTop);
            } else if (releasedChildLeft < -300 || xvel < -900) {
                if (mCallBack != null) {
                    mCallBack.leftExit(releasedChild);
                }
                releasedViewList.add((CardItemView) releasedChild);
                exitAnim(true,releasedChildLeft,releasedChildTop);
            }else{
                mViewDragHelper.settleCapturedViewAt(0, 0);
                invalidate();
            }
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (state == ViewDragHelper.STATE_IDLE && releasedViewList.size() != 0) {
                orderItem();
            }
        }
    }

    private CardItemView getTopCard(){
        return mViewList.get(0);
    }

    private void orderItem() {

        if (releasedViewList.size() == 0) {
            return;
        }

        Log.d("TAG", "loadItem() called with: " + "");

        for (int i = mViewList.size() - 1; i > 0; i--) {
            mViewList.get(i).bringToFront();
        }

        loadItem();
        CardItemView view = mViewList.get(0);

        view.reSet();

        mViewList.remove(view);
        mViewList.add(view);
        releasedViewList.remove(0);
    }



    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(false)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private void exitAnim(boolean isLeft,int changedViewLeft,int changedViewTop) {
        int finalLeft = 0;
        int finalTop = 0;

        CardItemView topView = mViewList.get(0);
        if (isLeft) {
            if ((-changedViewLeft) + changedViewTop != 0) {
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


    public void addCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public static class CallBack {

        void leftExit(View exitView) {
        }

        void rightExit(View exitView) {
        }

        void onScroll(CardItemView changedView, int dx, int dy) {
        }
    }
}