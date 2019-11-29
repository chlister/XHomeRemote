/**
 * Activity for view that presents the Outlet register
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.outletregister;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletregister.IOutletRegisterPresenter;
import com.xpower.xhomeremote.presenter.outletregister.OutletRegisterPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;

public class OutletRegisterActivity extends BaseActivity implements IOutletRegisterView {
    public static final String DATA_INTENT_OUTLET = "DATA_INTENT_OUTLET";
    private IOutletRegisterPresenter presenter;
    TextView mTypeTextView, mAgentIdTextView, mOutletIdTextView;
    EditText mNameEditText;
    Spinner mTypeSpinner;
    Button mButton;

    private Outlet item;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_register);
        presenter = new OutletRegisterPresenter(this);

        mTypeTextView = findViewById(R.id.register_Type);
        mAgentIdTextView = findViewById(R.id.register_AgentID);
        mOutletIdTextView = findViewById(R.id.register_OutletID);
        mNameEditText = findViewById(R.id.register_Name_input);
        mTypeSpinner = findViewById(R.id.register_Type_spinner);
        mButton = findViewById(R.id.register_button);

        item = (Outlet) getIntent().getSerializableExtra(DATA_INTENT_OUTLET);

        mTypeTextView.setText(item.type.name());
        mAgentIdTextView.setText(Integer.toString(item.agentId));
        mOutletIdTextView.setText(Integer.toString(item.id));
        mNameEditText.setText(item.name);

        ArrayAdapter<HomeApplianceType> adapter = new ArrayAdapter<HomeApplianceType>(this, android.R.layout.simple_list_item_1, HomeApplianceType.values());
        mTypeSpinner.setAdapter(adapter);
        mTypeSpinner.setSelection(item.type.ordinal());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.name = mNameEditText.getText().toString();
                item.type = HomeApplianceType.valueOf(mTypeSpinner.getSelectedItem().toString());
                presenter.registerOutlet(item);
            }
        });
    }

    @Override
    public void registerSuccesfull(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMessage("Outlet Updated");
            }
        });
        finish();
    }

    @Override
    public void registerFailed(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMessage("Register failed");
            }
        });
    }
}