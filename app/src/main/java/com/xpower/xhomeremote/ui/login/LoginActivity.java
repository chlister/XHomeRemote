package com.xpower.xhomeremote.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.presenter.login.ILoginPresenter;
import com.xpower.xhomeremote.presenter.login.LoginPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.socketlist.SocketListActivity;

public class LoginActivity extends BaseActivity implements ILoginView {
    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        findViewById(R.id.login_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.establishConnection("","");
            }
        });
    }

    @Override
    public void connectionFailed(String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    showMessage("Connection Failed");
            }
        });
    }

    @Override
    public void connectionSuccess(){
        Intent i = new Intent(this, SocketListActivity.class);
        startActivity(i);
    }
}
