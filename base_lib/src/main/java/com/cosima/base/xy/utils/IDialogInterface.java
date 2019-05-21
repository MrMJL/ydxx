package com.cosima.base.xy.utils;

import android.app.Dialog;
import java.util.List;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public interface IDialogInterface {


    /**
     * checkboxDialog的点击监听
     */
    interface OnCheckboxListener {
        /**
         * 点击监听
         * @param : checkbox是否被选中
         */
        void onClick(Dialog dialog, Boolean checked);
    }

    interface OnMenuListener {
        /**
         * @param position : 选中menu下标
         */
        void onClick(Dialog dialog, int  position);
    }


    interface OnMultiDialogListener {
        void onClick(Dialog dialog, int[] checks);
    }

    interface OnInputDialogListener {
        void onClick(Dialog dialog, String input);
    }

    interface OnDatePickListener {
        /**
         * 日期选择 返回
         *
         * @param month 月份是 0-11
         * @param date  日期拼接 , 月份已经+1
         */
        void onDatePick(int year, int month, int day,String date);
    }

    interface OnTimePickListener {

        void onTimePick(int hour, int minute);
    }
}
