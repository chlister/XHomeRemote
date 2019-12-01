package com.xpower.xhomeremote.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.XHomeRemote;
import com.xpower.xhomeremote.presenter.login.ILoginPresenter;
import com.xpower.xhomeremote.presenter.login.LoginPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;
import com.xpower.xhomeremote.ui.outletlist.OutletListActivity;

public class LoginActivity extends BaseActivity implements ILoginView {
    private ILoginPresenter mPresenter;
    private EditText mInternalIpEditView, mExternalIpEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(mInternalIpEditView.getText().toString(), mExternalIpEditView.getText().toString());
            }
        });
        mInternalIpEditView = findViewById(R.id.login_internal);
        mExternalIpEditView = findViewById(R.id.login_external);

        if(getIntent().getFlags() == Intent.FLAG_ACTIVITY_CLEAR_TOP)
            showMessage(getString(R.string.login_connectionfailed));
    }

    @Override
    protected void onResume() {
        mPresenter = ((XHomeRemote)getApplication()).getLoginPresenter(this);
    }

    @Override
    public void onConnectionSuccess(){
        Intent i = new Intent(this, OutletListActivity.class);
        startActivity(i);
    }
}
