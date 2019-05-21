package com.cosima.base.xy.test.example;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.cosima.base.xy.base.CallBack;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public class MainModelImpl implements MainController.MainModel {
    private boolean first = true;

    @Override
    public void login(Context context, String account, String password, CallBack callBack) {
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(context, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (first) {
            first = false;
            callBack.onFailed("登录失败");
        } else {
            first = true;
            callBack.onSuccess();
        }
    }

}
