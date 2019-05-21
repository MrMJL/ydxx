package com.cosima.base.xy.test.example;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.cosima.base.xy.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 *  MVP模板
 */
public class MainActivity extends RxAppCompatActivity implements MainController.MainView {


    private EditText account;
    private EditText password;
    MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = findViewById(R.id.edit_account);
        password = findViewById(R.id.edit_password);
        mainPresenter = new MainPresenterImpl(this,this);
    }

    public void login(View view) {
        //mainPresenter.login(account.getText().toString(), password.getText().toString());
    }


    @Override
    public void onSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String msg, String errorCode) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
