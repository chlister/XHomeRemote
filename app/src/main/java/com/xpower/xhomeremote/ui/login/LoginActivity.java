/**
 * Activity for view the presents a login screen
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
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

    /**
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mInternalIpEditView = findViewById(R.id.login_internal);
        mExternalIpEditView = findViewById(R.id.login_external);

        findViewById(R.id.login_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(mInternalIpEditView.getText().toString(), mExternalIpEditView.getText().toString());
            }
        });

        // Check if Activity is started by a ConnectionFailed
        if(getIntent().getFlags() == Intent.FLAG_ACTIVITY_CLEAR_TOP)
            showMessage(getString(R.string.login_connectionfailed));
    }

    /**
     * We inject the callback onResume to insure that the active Presenter/Activity gets the callback
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter = ((XHomeRemote)getApplication()).getLoginPresenter(this);
    }

    /**
     * start the OutletListActivity if connection was Successful
     * @author  Martin J. J.
     * @since   11/26/2019
     * @status  Done
     */
    @Override
    public void onConnectionSuccess(){
        Intent i = new Intent(this, OutletListActivity.class);
        startActivity(i);
    }

}
