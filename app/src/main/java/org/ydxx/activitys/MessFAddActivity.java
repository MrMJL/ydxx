package org.ydxx.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;
import org.ydxx.entity.ResultMessage;

public class MessFAddActivity extends BaseActivity {
    private Button  btn_mess_fadd_save;
    private ImageView btn_mess_fadd_return;

    private EditText et_mess_fadd_fmess;
    private ProgressDialog progressDialog = null;
    private Handler handler;
    private Declare declare;
    private TextView tv_title;
    Jxzy jxzy;
    private boolean isThree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_fadd);
        declare = (Declare) this.getApplicationContext();

        et_mess_fadd_fmess = (EditText) findViewById(R.id.et_mess_fadd_fmess);
        tv_title = (TextView) findViewById(R.id.tv_mess_fadd_title);

        jxzy = (Jxzy) getIntent().getSerializableExtra("jxzy");
        isThree = getIntent().getBooleanExtra("isThree", false);

        if (jxzy != null) {
            tv_title.setText("修改课程");
        } else {
            if (declare.getUser().getType().equals("3")) {
                tv_title.setText("我的留言");
            } else {
                if (isThree) {
                    tv_title.setText("我的留言");
                } else {
                    tv_title.setText("添加课程");
                }
            }
        }
        btn_mess_fadd_return = (ImageView) findViewById(R.id.btn_mess_fadd_return);
        btn_mess_fadd_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (declare.getUser().getType().equals("3")) {
                    Intent intent = new Intent(MessFAddActivity.this, MainActivity.class);
                    startActivity(intent);
                    MessFAddActivity.this.finish();
                } else {
                    Intent intent = new Intent(MessFAddActivity.this, JxzyActivity.class);
                    startActivity(intent);
                    MessFAddActivity.this.finish();
                }
            }
        });

        btn_mess_fadd_save = (Button) findViewById(R.id.btn_mess_fadd_save);
        btn_mess_fadd_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_mess_fadd_fmess.getText().equals("")) {
                    progressDialog =
                        ProgressDialog.show(MessFAddActivity.this, "", "数据保存中.....", true);
                    if (jxzy != null) {
                        jxzy.setKcmc(et_mess_fadd_fmess.getText().toString());
                        jxzy.setLsxm(declare.getUser().getUsername());
                        new Thread(new updateJxzyRunable(jxzy)).start();
                    } else {
                        if (declare.getUser().getType().equals("3")) {
                            Mess mess = new Mess();
                            mess.setFmessage(et_mess_fadd_fmess.getText().toString());
                            mess.setFuserid(declare.getUser().getId() + "");
                            mess.setFusername(declare.getUser().getUsername());
                            new Thread(new AddMessRunable(mess)).start();
                        } else {
                            if (isThree) {
                                Mess mess = new Mess();
                                mess.setFmessage(et_mess_fadd_fmess.getText().toString());
                                mess.setFuserid(declare.getUser().getId() + "");
                                mess.setFusername(declare.getUser().getUsername());
                                new Thread(new AddMessRunable(mess)).start();
                            } else {
                                Jxzy jxzy = new Jxzy();
                                jxzy.setKcmc(et_mess_fadd_fmess.getText().toString());
                                jxzy.setExt1("http://www.w3school.com.cn/");
                                jxzy.setLsxm(declare.getUser().getUsername());
                                new Thread(new AddJxzyRunable(jxzy)).start();
                            }
                        }
                    }
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
                    Intent intent = new Intent(MessFAddActivity.this, MessActivity.class);
                    startActivity(intent);
                    MessFAddActivity.this.finish();
                } else if (resultMessage.isStatus() && msg.what == 2) {
                    Toast.makeText(MessFAddActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MessFAddActivity.this, JxzyActivity.class);
                    startActivity(intent);
                    MessFAddActivity.this.finish();
                } else if (resultMessage.isStatus() && msg.what == 3) {
                    Toast.makeText(MessFAddActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MessFAddActivity.this, JxzyActivity.class);
                    startActivity(intent);
                    MessFAddActivity.this.finish();
                } else {
                    Toast.makeText(MessFAddActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (declare.getUser().getType().equals("3")) {
            Intent intent = new Intent(MessFAddActivity.this, MainActivity.class);
            startActivity(intent);
            MessFAddActivity.this.finish();
        } else {
            Intent intent = new Intent(MessFAddActivity.this, JxzyActivity.class);
            startActivity(intent);
            MessFAddActivity.this.finish();
        }
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
            LocalDataSource.getInstance(MessFAddActivity.this).getMessDao().insertMess(mess);
            resultMessage.setMessage("添加成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }

    class AddJxzyRunable implements Runnable {
        private Jxzy jxzy;

        public AddJxzyRunable(Jxzy jxzy) {
            this.jxzy = jxzy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 2;
            LocalDataSource.getInstance(MessFAddActivity.this).getJxzyDao().insertJxzy(jxzy);
            resultMessage.setMessage("添加成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }

    class updateJxzyRunable implements Runnable {
        private Jxzy jxzy;

        public updateJxzyRunable(Jxzy jxzy) {
            this.jxzy = jxzy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 3;
            LocalDataSource.getInstance(MessFAddActivity.this).getJxzyDao().updateJxzy(jxzy);
            resultMessage.setStatus(true);
            resultMessage.setMessage("修改成功!");
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }
}
