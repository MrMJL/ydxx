package org.ydxx.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;
import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Mess;
import org.ydxx.entity.ResultMessage;
import org.ydxx.net.MainService;
import org.ydxx.net.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessTAddActivity extends BaseActivity {
    private ImageView btn_mess_tadd_return;
    private Button  btn_mess_tadd_save;

    private EditText et_mess_tadd_tmess;
    private ProgressDialog progressDialog = null;
    private Handler handler;
    private Declare declare;
    private Mess mess1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_tadd);
        declare = (Declare) this.getApplicationContext();

        Intent intent = this.getIntent();
        mess1 = (Mess) intent.getSerializableExtra("mess");

        et_mess_tadd_tmess = (EditText) findViewById(R.id.et_mess_tadd_tmess);

        btn_mess_tadd_return = (ImageView) findViewById(R.id.btn_mess_tadd_return);
        btn_mess_tadd_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessTAddActivity.this, MainActivity.class);
                startActivity(intent);
                MessTAddActivity.this.finish();
            }
        });

        btn_mess_tadd_save = (Button) findViewById(R.id.btn_mess_tadd_save);
        btn_mess_tadd_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_mess_tadd_tmess.getText().equals("")) {
                    progressDialog =
                        ProgressDialog.show(MessTAddActivity.this, "", "数据保存中.....", true);
                    mess1.setTmessage(et_mess_tadd_tmess.getText().toString());
                    mess1.setTusername(declare.getUser().getUsername());

                    new Thread(new AddMessRunable(mess1)).start();
                }
            }
        });

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                ResultMessage resultMessage = (ResultMessage) msg.obj;
                if (resultMessage.isStatus() && msg.what == 1) {
                    Intent intent = new Intent(MessTAddActivity.this, MessActivity.class);
                    startActivity(intent);
                    MessTAddActivity.this.finish();
                } else if (resultMessage.isStatus() && msg.what == 2) {

                } else {
                    Toast.makeText(MessTAddActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MessTAddActivity.this, MainActivity.class);
        startActivity(intent);
        MessTAddActivity.this.finish();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    class AddMessRunable implements Runnable {
        private Mess mess;

        public AddMessRunable(Mess mess) {
            this.mess = mess;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
//            RetrofitClient.getInstance().create(MainService.class)
//                    .updateData(type, json)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnSubscribe(disposable -> {
//                    })
//                    .subscribe(response -> {
//                        Log.e("kin", "updateData: " + response.toString());
//                        JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                        if (jsonObject.getInt("status") == 1) {
//
//                        }else {
//
//                        }
//                    }, throwable -> {
//                        Log.e("kin", "updateData: " + throwable.getMessage());
//                    });
            LocalDataSource.getInstance(MessTAddActivity.this).getMessDao().updateUser(mess);
            resultMessage.setMessage("回复成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }
}
