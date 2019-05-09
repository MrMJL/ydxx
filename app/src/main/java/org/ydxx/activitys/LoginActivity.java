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
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.ResultMessage;
import org.ydxx.entity.User;

public class LoginActivity extends BaseActivity {
    private EditText userName, password;
    private TextView tv_registerLink;
    private Button btn_login;
    private ProgressDialog progressDialog = null;
    private Handler loginHandler;
    private Declare declare;

    private String userNameValue, passwordValue;

    @SuppressLint("HandlerLeak")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declare = (Declare) this.getApplicationContext();
        userName = (EditText) findViewById(R.id.et_user_name);
        password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_registerLink = (TextView) findViewById(R.id.tv_registerLink);
        btn_login.setOnClickListener(new LoginButtonListener());

        tv_registerLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegeditActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        loginHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                ResultMessage resultMessage = (ResultMessage) msg.obj;
                if (resultMessage.isStatus()) {
                    declare.setUser(resultMessage.getUser());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    //注册完成后，会回调这个方法
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { // 根据状态码，处理返回结果
            case RESULT_OK:
                Bundle bundle = data.getExtras(); // 获取intent里面的bundle对象
                String username = bundle.getString("username");
                this.userName.setText(username);
                break;
            default:
                break;
        }
    }

    class LoginButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            boolean ischeck = true;
            userNameValue = userName.getText().toString();
            passwordValue = password.getText().toString();

            if (userNameValue == null || userNameValue.equals("")) {
                ischeck = false;
                userName.setError("用户名不能为空！");
            }
            if (passwordValue == null || passwordValue.equals("")) {
                ischeck = false;
                password.setError("密码不能为空！");
            }
            if (!ischeck) {
                return;
            }
            progressDialog = ProgressDialog.show(LoginActivity.this, "", "系统登陆中.....", true);
            progressDialog.setCancelable(true);// 当点击按钮返回的时候Dialog消失
            new Thread(new Runnable() {

                @Override
                public void run() {
                    ResultMessage resultMessage = new ResultMessage();
                    Message message = new Message();
                    message.what = 1;
                    List<User> allUser =
                        LocalDataSource.getInstance(LoginActivity.this).getUserDao().getAllUser();
                    boolean isStatus = false;
                    User user = new User();
                    if (allUser.size() == 0) {
                        resultMessage.setMessage("当前账号未注册，请先注册");
                    } else {
                        for (int i = 0; i < allUser.size(); i++) {
                            Log.e("TAG",
                                "run:" + allUser.get(i).getUsername() + "===" + allUser.get(i)
                                    .getPassword());
                            Log.e("TAG",
                                "run: NAME" + allUser.get(i).getUsername().equals(userNameValue));
                            Log.e("TAG", "run: PSW" + allUser.get(i).getPassword().equals(passwordValue));
                            if (allUser.get(i).getUsername().equals(userNameValue) && allUser.get(i)
                                .getPassword()
                                .equals(passwordValue)) {
                                user = allUser.get(i);
                                isStatus = true;
                                break;
                            } else {
                                isStatus = false;
                                resultMessage.setMessage("账户或密码错误，请重新输入");
                            }
                        }
                    }
                    //nichou ！
                    resultMessage.setUser(user);
                    resultMessage.setStatus(isStatus);
                    message.obj = resultMessage;
                    loginHandler.sendMessage(message);
                }
            }).start();
        }
    }
}