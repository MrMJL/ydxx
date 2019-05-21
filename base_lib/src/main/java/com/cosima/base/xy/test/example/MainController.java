package com.cosima.base.xy.test.example;

import android.content.Context;
import com.cosima.base.xy.base.BasePresenter;
import com.cosima.base.xy.base.BaseView;
import com.cosima.base.xy.base.CallBack;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public interface MainController {

    /**
     * model
     */
    interface MainModel  {
        void login(Context context, String account,String password,CallBack callBack);
    }

    /**
     * view
     */
    interface MainView extends BaseView {
    }

    /**
     * presenter
     */
    abstract class MainPresenter extends
        BasePresenter<MainView,MainModel> {
        public abstract void login(String account, String password);
    }
}
