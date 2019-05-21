package com.cosima.base.xy.base;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public abstract class BasePresenter<V,M> {
    public V view;
    public M model;

    public void setVM(V view, M model) {
        this.model = model;
        this.view = view;
    }
}
