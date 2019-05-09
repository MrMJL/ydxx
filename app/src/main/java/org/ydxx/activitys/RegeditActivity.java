package org.ydxx.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.ResultMessage;
import org.ydxx.entity.User;

public class RegeditActivity extends BaseActivity {

    private static EditText txt_username, txt_password, txt_email, txt_age;
    private static RadioButton rdo_man;
    private static Button btn_regedit, btn_return;
    private static RadioGroup rg_sex_group;

    private ProgressDialog progressDialog = null;
    private Handler regeditHandler;
    private String usernameValue, passwordValue, emailVallue, sexValue = "1";
    private Integer ageValue;
    private Declare declare;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regedit);
        declare = (Declare) this.getApplicationContext();

        txt_username = (EditText) this.findViewById(R.id.et_user_name);
        txt_password = (EditText) this.findViewById(R.id.et_reg_password);
        txt_email = (EditText) this.findViewById(R.id.et_reg_email);
        txt_age = (EditText) this.findViewById(R.id.et_reg_age);
        rdo_man = (RadioButton) this.findViewById(R.id.rb_man);

        btn_regedit = (Button) this.findViewById(R.id.btn_regedit);
        btn_return = (Button) findViewById(R.id.btn_users_return);

        btn_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegeditActivity.this, LoginActivity.class);
                RegeditActivity.this.setResult(RESULT_CANCELED, intent);
                RegeditActivity.this.finish();
            }
        });
        btn_regedit.setOnClickListener(new RegeditButtonListener());

        rg_sex_group = (RadioGroup) this.findViewById(R.id.rg_sex_group);
        rg_sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rdo_man.getId()) {
                    sexValue = "1";
                } else {
                    sexValue = "2";
                }
            }
        });
        regeditHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) progressDialog.cancel();
                if (((ResultMessage) msg.obj).isStatus()) {
                    Toast.makeText(RegeditActivity.this, ((ResultMessage) msg.obj).getMessage(),
                        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegeditActivity.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", txt_username.getText().toString());
                    intent.putExtras(bundle);
                    RegeditActivity.this.setResult(RESULT_OK, intent);
                    RegeditActivity.this.finish();
                } else {
                    Toast.makeText(RegeditActivity.this, ((ResultMessage) msg.obj).getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    class RegeditButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {

            usernameValue = txt_username.getText().toString();
            passwordValue = txt_password.getText().toString();

            emailVallue = txt_email.getText().toString();

            ageValue = Integer.parseInt(
                txt_age.getText().toString().equals("") ? "0" : txt_age.getText().toString());

            boolean isCheck = true;

            if (usernameValue == null || usernameValue.equals("")) {
                txt_username.setError("用户名不能为空！");
                isCheck = false;
            }
            if (passwordValue == null || passwordValue.equals("")) {
                txt_password.setError("密码不能为空！");
                isCheck = false;
            }

            if (ageValue == null || ageValue.equals("")) {
                txt_age.setError("年龄不能为空！");
                isCheck = false;
            }
            if (emailVallue == null || emailVallue.equals("")) {
                txt_email.setError("电子邮件不能为空！");
                isCheck = false;
            }

            if (!isCheck) {
                return;
            }

            progressDialog = ProgressDialog.show(RegeditActivity.this, "", "正在提交数据.....", true);
            progressDialog.setCancelable(true);// 当点击按钮返回的时候Dialog消失
            User user = new User();
            user.setAge(ageValue + "");
            user.setEmail(emailVallue);

            user.setPassword(passwordValue);
            user.setSex(sexValue);
            user.setUsername(usernameValue);
            user.setType("4");
            new Thread(new RegeditRunable(user)).start();
        }
    }

    class RegeditRunable implements Runnable {
        private User user;

        public RegeditRunable(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
            LocalDataSource.getInstance(RegeditActivity.this)
                .getUserDao()
                .insertUser(user);
            resultMessage.setMessage("注册成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            regeditHandler.sendMessage(message);
        }
    }
}
