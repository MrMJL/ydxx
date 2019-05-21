package com.cosima.base.xy.base;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public interface BaseView {
    void onSuccess();

    void onFailed(String msg, String errorCode);
}
