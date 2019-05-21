package com.cosima.base.xy.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.cosima.base.xy.R;

/**
 * 工具类
 */

public class Tools {

    private static Tools tools;
    private Dialog dialog;

    public static Tools getInstance() {
        if (tools == null) {
            synchronized (Tools.class) {
                if (tools == null) {
                    tools = new Tools();
                }
            }
        }
        return tools;
    }

    /**
     * 旋转进度条显示对话框
     */
    public void showProgress(Context context, String con) {
        disMissDial();
        dialog = new Dialog(context, R.style.MyDialogTheme);
        dialog.setContentView(R.layout.dial_pro);
        dialog.setCancelable(false);
        TextView tv = dialog.findViewById(R.id.dp_pro_con);
        tv.setText(con);
        dialog.show();
    }

    /**
     * 旋转进度条显示对话框
     */
    public void showProgressSyncOk(Context context, String con) {
        disMissDial();
        dialog = new Dialog(context, R.style.MyDialogTheme);
        dialog.setContentView(R.layout.dial_pro_complete);
        dialog.setCancelable(false);
        TextView tv = dialog.findViewById(R.id.dpc_pro_con);
        tv.setText(con);
        dialog.show();
    }
    /**
     * 旋转进度条显示对话框2
     */
    public void showProgressSyncOk2(Context context, String con) {
        disMissDial();
        dialog = new Dialog(context, R.style.MyDialogTheme);
        dialog.setContentView(R.layout.dial_pro_complete2);
        dialog.setCancelable(false);
        TextView tv = dialog.findViewById(R.id.dpc_pro_con);
        tv.setText(con);
        dialog.show();
    }

    /**
     * 关闭弹出框
     */
    public void disMissDial() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 打印调用栈
     */
    public void mkLog(){
        RuntimeException here = new RuntimeException("here");
        here.fillInStackTrace();
        Log.w("scj", "Called: " + this, here);
    }

}
