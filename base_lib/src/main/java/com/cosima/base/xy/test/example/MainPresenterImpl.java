package com.cosima.base.xy.test.example;

import android.content.Context;
import com.cosima.base.xy.base.CallBack;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public class MainPresenterImpl extends MainController.MainPresenter {
    private Context context;

    public MainPresenterImpl(MainController.MainView view, Context context) {
        setVM(view, new MainModelImpl());
        this.context = context;
    }

    @Override
    public void login(String account, String password) {
        model.login(context, account, password, new CallBack() {
            @Override
            public void onSuccess() {
                view.onSuccess();
            }

            @Override
            public void onFailed(String msg) {
                view.onFailed(msg,"");
            }
        });

    }
}
