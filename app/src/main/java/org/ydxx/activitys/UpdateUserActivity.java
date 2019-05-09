package org.ydxx.activitys;

import android.annotation.SuppressLint;
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

public class UpdateUserActivity extends BaseActivity {

    private static EditText et_update_user_name, et_update_password, et_update_age, et_update_email;
    private static RadioButton rb_update_man;
    private static RadioButton rb_update_women;
    private static Button btn_update, btn_update_users_return;
    private static RadioGroup rg_update_sex_group;

    private ProgressDialog progressDialog = null;
    private Handler regeditHandler;
    private String usernameValue, passwordValue, emailVallue, sexValue = "1";
    private Integer ageValue;
    private Declare declare;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateuser);
        declare = (Declare) this.getApplicationContext();

        et_update_user_name = (EditText) this.findViewById(R.id.et_update_user_name);
        et_update_password = (EditText) this.findViewById(R.id.et_update_password);
        et_update_email = (EditText) this.findViewById(R.id.et_update_email);
        et_update_age = (EditText) this.findViewById(R.id.et_update_age);
        rb_update_man = (RadioButton) this.findViewById(R.id.rb_update_man);
        rb_update_women = (RadioButton) this.findViewById(R.id.rb_update_women);

        btn_update = (Button) this.findViewById(R.id.btn_update);
        btn_update_users_return = (Button) findViewById(R.id.btn_update_users_return);

        btn_update_users_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateUserActivity.this, MainActivity.class);
                //UpdateUserActivity.this.setResult(RESULT_CANCELED, intent);
                startActivity(intent);
                UpdateUserActivity.this.finish();
            }
        });
        btn_update.setOnClickListener(new RegeditButtonListener());

        rg_update_sex_group = (RadioGroup) this.findViewById(R.id.rg_update_sex_group);
        rg_update_sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_update_man.getId()) {
                    sexValue = "1";
                } else {
                    sexValue = "2";
                }
            }
        });
        et_update_age.setText(declare.getUser().getAge());
        et_update_user_name.setText(declare.getUser().getUsername());
        et_update_password.setText(declare.getUser().getPassword());
        et_update_email.setText(declare.getUser().getEmail());
        if (declare.getUser().getSex().equals("1")) {
            sexValue = "1";
            rb_update_man.setChecked(true);
            rb_update_women.setChecked(false);
        } else {
            sexValue = "2";
            rb_update_man.setChecked(false);
            rb_update_women.setChecked(true);
        }
        regeditHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                if (((ResultMessage) msg.obj).isStatus()) {
                    Toast.makeText(UpdateUserActivity.this, ((ResultMessage) msg.obj).getMessage(),
                        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateUserActivity.this, LoginActivity.class);
                    UpdateUserActivity.this.startActivity(intent);
                    UpdateUserActivity.this.finish();
                } else {
                    Toast.makeText(UpdateUserActivity.this, ((ResultMessage) msg.obj).getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    class RegeditButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            usernameValue = et_update_user_name.getText().toString();
            passwordValue = et_update_password.getText().toString();
            emailVallue = et_update_email.getText().toString();
            ageValue = Integer.parseInt(et_update_age.getText().toString().equals("") ? "0"
                : et_update_age.getText().toString());
            boolean isCheck = true;
            if (usernameValue == null || usernameValue.equals("")) {
                et_update_user_name.setError("用户名不能为空！");
                isCheck = false;
            }
            if (passwordValue == null || passwordValue.equals("")) {
                et_update_password.setError("密码不能为空！");
                isCheck = false;
            }
            if (ageValue == null || ageValue.equals("")) {
                et_update_age.setError("年龄不能为空！");
                isCheck = false;
            }
            if (emailVallue == null || emailVallue.equals("")) {
                et_update_email.setError("电子邮件不能为空！");
                isCheck = false;
            }
            if (!isCheck) {
                return;
            }

            progressDialog = ProgressDialog.show(UpdateUserActivity.this, "", "正在提交数据.....", true);
            progressDialog.setCancelable(true);// 当点击按钮返回的时候Dialog消失
            User user = new User();
            user.setId(declare.getUser().getId());
            user.setType(declare.getUser().getType());
            user.setAge(ageValue + "");
            user.setEmail(emailVallue);

            user.setPassword(passwordValue);
            user.setSex(sexValue);
            user.setUsername(usernameValue);

            new Thread(new UpdateUserRunable(user)).start();
        }
    }

    class UpdateUserRunable implements Runnable {
        private User user;

        public UpdateUserRunable(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
            LocalDataSource.getInstance(UpdateUserActivity.this).getUserDao().updateUser(user);
            resultMessage.setMessage("更新成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            regeditHandler.sendMessage(message);
        }
    }
}
