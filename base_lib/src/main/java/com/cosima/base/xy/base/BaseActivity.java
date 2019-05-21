package com.cosima.base.xy.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import butterknife.ButterKnife;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initPresenter();
    }

    @Override protected void onDestroy() {
        super.onDestroy();

    }

    // 点击空白区域 自动隐藏软键盘
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            //点击空白位置 隐藏软键盘
            InputMethodManager mInputMethodManager =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    public void toOtherActivity(Activity nowActivity, Activity targetActivity) {
        Intent intent = new Intent(nowActivity,targetActivity.getClass());
        startActivity(intent);
    }

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 获取布局
     */
    public abstract int getLayoutId();

    /**
     * 初始化Data
     */
    public abstract void initData();

    /**
     * 初始化 Presenter
     */
    public abstract void initPresenter();
}
