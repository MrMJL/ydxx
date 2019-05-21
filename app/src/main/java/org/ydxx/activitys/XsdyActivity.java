package org.ydxx.activitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.ydxx.R;
import org.ydxx.adapters.XsdyItemAdapter;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.ResultMessage;
import org.ydxx.entity.Xsdy;
import org.ydxx.net.MainService;
import org.ydxx.net.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 我的课程（学生）
 */
public class XsdyActivity extends BaseActivity {
    private static final String[] operationTypes = new String[] { "下载", "退订" };
    private ImageView btn_xsdy_return;
    private XsdyItemAdapter xsdyItemAdapter;
    private ListView lv_xsdy;

    private ProgressDialog progressDialog = null;
    private Handler handler;
    private Declare declare;

    @SuppressLint("HandlerLeak") @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xsdy);
        declare = (Declare) this.getApplicationContext();

        btn_xsdy_return = (ImageView) findViewById(R.id.btn_xsdy_return);
        btn_xsdy_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsdyActivity.this, MainActivity.class);
                startActivity(intent);
                XsdyActivity.this.finish();
            }
        });

        lv_xsdy = (ListView) findViewById(R.id.lv_xsdy);
        xsdyItemAdapter = new XsdyItemAdapter(this.getBaseContext());
        lv_xsdy.setAdapter(xsdyItemAdapter);
        lv_xsdy.setOnItemLongClickListener(new ItemOnItemLongClickListener());

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                ResultMessage resultMessage = (ResultMessage) msg.obj;
                if (resultMessage.isStatus() && msg.what == 1) {
                    List<Xsdy> xsdys = (List<Xsdy>) resultMessage.getItems();
                    if (!xsdys.isEmpty()) {
                        xsdyItemAdapter.setItems(xsdys);
                    } else {
                        xsdyItemAdapter.setItems(new ArrayList<Xsdy>());
                        Toast.makeText(XsdyActivity.this, "暂未订阅课程", Toast.LENGTH_SHORT).show();
                    }
                    xsdyItemAdapter.notifyDataSetChanged();
                } else if (resultMessage.isStatus() && msg.what == 2) {
                    //progressDialog = ProgressDialog.show(XsdyActivity.this, "", "查找中.....", true);
                    //progressDialog.setCancelable(true);
                    List<Xsdy> xsdys = (List<Xsdy>) resultMessage.getItems();
                    if (!xsdys.isEmpty()) {
                        xsdyItemAdapter.setItems(xsdys);
                    } else {
                        xsdyItemAdapter.setItems(new ArrayList<Xsdy>());
                        Toast.makeText(XsdyActivity.this, "暂未订阅课程", Toast.LENGTH_SHORT).show();
                    }
                    xsdyItemAdapter.notifyDataSetChanged();
                    //XsdyDao xsdy = new XsdyDao();
                    //xsdy.setXsid(declare.getUser().getId());
                    //new Thread(new findXsdyRunable(xsdy)).start();
                } else {
                    Toast.makeText(XsdyActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    class ItemOnItemLongClickListener implements OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            final Xsdy xsdy = (Xsdy) parent.getAdapter().getItem(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(XsdyActivity.this);
            builder.setTitle("选择操作");
            builder.setItems(operationTypes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    switch (which) {
                        case 0:
                            //下载使用系统浏览器请求Url
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(xsdy.getExt1());
                            intent.setData(content_url);
                            startActivity(intent);
                            break;
                        case 1:
                            progressDialog =
                                ProgressDialog.show(XsdyActivity.this, "", "退订中.....", true);
                            progressDialog.setCancelable(true);

                            new Thread(new deleteXsdyRunable(xsdy)).start();
                            break;
                        default:
                            break;
                    }
                }
            });
            builder.show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(XsdyActivity.this, MainActivity.class);
        startActivity(intent);
        XsdyActivity.this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog = ProgressDialog.show(XsdyActivity.this, "", "查找中.....", true);
        progressDialog.setCancelable(true);
        Xsdy xsdy = new Xsdy();
        xsdy.setXsid(declare.getUser().getId() + "");
        new Thread(new findXsdyRunable(xsdy)).start();
    }

    List<Xsdy> items = new ArrayList<>();

    class findXsdyRunable implements Runnable {
        private Xsdy xsdy;

        public findXsdyRunable(Xsdy xsdy) {
            this.xsdy = xsdy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
            List<Xsdy> allXsdy =
                LocalDataSource.getInstance(XsdyActivity.this).getXsdyDao().getAllXsdy();
            resultMessage.setStatus(true);
            resultMessage.setItems(allXsdy);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }

    class deleteXsdyRunable implements Runnable {
        private Xsdy xsdy;

        public deleteXsdyRunable(Xsdy xsdy) {
            this.xsdy = xsdy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 2;
//            RetrofitClient.getInstance().create(MainService.class)
//                    .delData(type, json)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnSubscribe(disposable -> {
//                    })
//                    .subscribe(response -> {
//                        Log.e("kin", "delData: " + response.toString());
//                        JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                        if (jsonObject.getInt("status") == 1) {
//
//                        }else {
//
//                        }
//                    }, throwable -> {
//                        Log.e("kin", "delData: " + throwable.getMessage());
//                    });
            LocalDataSource.getInstance(XsdyActivity.this)
                .getXsdyDao()
                .deleteXsdyBy(xsdy.getJxzymc());
            List<Xsdy> allXsdy =
                LocalDataSource.getInstance(XsdyActivity.this).getXsdyDao().getAllXsdy();
            resultMessage.setStatus(true);
            resultMessage.setItems(allXsdy);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }
}
