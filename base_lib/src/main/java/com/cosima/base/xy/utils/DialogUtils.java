package com.cosima.base.xy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.text.InputType;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

/**
 * @author： Name:  JAG
 * @date: Date:  2018/12/21
 * @description:
 */
public class DialogUtils {

    private Dialog mDialog;

    public void showNormalDialog(Activity activity, String title, String msg, String affirMsg,
        QMUIDialogAction.ActionListener listener, String cancelMsg, Boolean cancelable) {

        dismissDialog();
        QMUIDialog.MessageDialogBuilder builder = new QMUIDialog.MessageDialogBuilder(activity)
            .setTitle(title)
            .setMessage(msg);

        if (cancelable && "".equals(cancelMsg)) {
            builder.addAction(cancelMsg, ((dialog, index) -> {
                dialog.dismiss();
            }));
        }
        builder.addAction(affirMsg, listener);
        mDialog = builder.create();
        mDialog.setCancelable(cancelable);
        showDialog(activity);
    }

    public void showCheckboxDialog(Activity activity, String title, String msg, String affirMsg,
        Boolean checked, IDialogInterface.OnCheckboxListener listener, String cancelMsg,
        Boolean cancelable) {

        dismissDialog();
        QMUIDialog.CheckBoxMessageDialogBuilder builder =
            new QMUIDialog.CheckBoxMessageDialogBuilder(activity)
                .setTitle(title)
                .setMessage(msg)
                .addAction(cancelMsg, ((dialog, index) -> {
                    dialog.dismiss();
                }));
        builder.addAction(affirMsg, ((dialog, index) ->
            listener.onClick(dialog, builder.isChecked()))).setChecked(checked);

        mDialog = builder.create();
        mDialog.setCancelable(cancelable);
        showDialog(activity);
    }

    /**
     * 列表dialog
     * 单选，不显示选择框
     */
    public void showMenuDialog(Activity activity, String[] items,
        DialogInterface.OnClickListener listener) {
        dismissDialog();
        mDialog = new QMUIDialog.MenuDialogBuilder(activity)
            .addItems(items, listener)
            .create();
        showDialog(activity);
    }

    /**
     * 列表dialog
     * 单选，显示选择框
     */
    public void showMenuCheckDialog(Activity activity, String[] items, int checkPosition,
        DialogInterface.OnClickListener listener) {
        dismissDialog();
        mDialog = new QMUIDialog.CheckableDialogBuilder(activity)
            .addItems(items, listener)
            .setCheckedIndex(checkPosition)
            .create();
        showDialog(activity);
    }

    /**
     * 列表dialog
     * 可多选
     */
    public void showCheckMultiDialog(Activity activity, String[] items, int[] checks,
        String affirMsg, IDialogInterface.OnMultiDialogListener listener) {
        dismissDialog();
        QMUIDialog.MultiCheckableDialogBuilder builder =
            new QMUIDialog.MultiCheckableDialogBuilder(activity)
                .addItems(items, null)
                .setCheckedItems(checks)
                .addAction("取消", ((dialog, index) -> {
                    dialog.dismiss();
                }));
        builder.addAction(affirMsg,
            ((dialog, index) -> listener.onClick(dialog, builder.getCheckedItemIndexes())));
        mDialog = builder.create();
        showDialog(activity);
    }

    /**
     * 输入Dialog
     */
    public void showInputDialog(Activity activity, String title, String hint, String affirMsg,
        IDialogInterface.OnInputDialogListener listener, int inputType) {
        dismissDialog();
        if (inputType == 0) {
            inputType = InputType.TYPE_CLASS_TEXT;
        }
        QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(activity)
            .setInputType(inputType)
            .setTitle(title)
            .setPlaceholder(hint)
            .addAction("取消", ((dialog, index) -> {
                dialog.dismiss();
            }));
        builder.addAction(affirMsg, ((dialog, index) -> listener.onClick(dialog,
            builder.getEditText().getText().toString())));
        mDialog = builder.create();
        showDialog(activity);
    }

    /**
     * 日期选择dialog
     */
    public void showDatePickerDialog(Activity activity, int yearNow, int monthNow,
        int dayOfMonthNow,
        IDialogInterface.OnDatePickListener listener) {
        dismissDialog();

        mDialog =
            new DatePickerDialog(activity, (view, year, month, dayOfMonth) -> {
                String date = year + "-" + month + "-" + dayOfMonth;
                listener.onDatePick(year, month, dayOfMonth, date);
            }, yearNow, monthNow, dayOfMonthNow);
        showDialog(activity);
    }

    /**
     * 时间选择dialog
     * @param activity
     * @param hourNow
     * @param minuteNow
     * @param listener
     * @param is24HourView
     */
    public void showTimePickerDialog(Activity activity, int hourNow, int minuteNow,
        IDialogInterface.OnTimePickListener listener, boolean is24HourView) {
        dismissDialog();

        mDialog =
            new TimePickerDialog(activity, (view, hour, minute) -> {
                listener.onTimePick(hour, minute);
            }, hourNow, minuteNow, is24HourView);
        showDialog(activity);
    }

    /**
     * 展示Dialog
     * 做了Activity的非空与finish判断
     */
    private void showDialog(Activity activity) {
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing()) {
            mDialog.show();
        }
    }

    /**
     * 关闭并释放Dialog
     */
    private void dismissDialog() {
        mDialog.dismiss();
        mDialog = null;
    }
}
