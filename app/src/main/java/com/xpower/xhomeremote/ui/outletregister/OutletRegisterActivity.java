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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;
import com.xpower.xhomeremote.presenter.outletregister.IOutletRegisterPresenter;
import com.xpower.xhomeremote.presenter.outletregister.OutletRegisterPresenter;
import com.xpower.xhomeremote.ui.base.BaseActivity;

public class OutletRegisterActivity extends BaseActivity implements IOutletRegisterView {
    public static final String DATA_INTENT_OUTLET = "DATA_INTENT_OUTLET";
    private IOutletRegisterPresenter presenter;
    private TextView mTypeTextView, mAgentIdTextView, mOutletIdTextView;
    private ImageView mIconView;
    private EditText mNameEditText;
    private Spinner mTypeSpinner;
    private Button mRegisterButton;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new OutletRegisterPresenter(this);
        getSupportActionBar().setTitle(this.getString(R.string.registerOutletTitle));

        mTypeTextView = findViewById(R.id.register_Type);
        mAgentIdTextView = findViewById(R.id.register_AgentID);
        mOutletIdTextView = findViewById(R.id.register_OutletID);
        mNameEditText = findViewById(R.id.register_Name_input);
        mTypeSpinner = findViewById(R.id.register_Type_spinner);
        mRegisterButton = findViewById(R.id.register_button);
        mIconView = findViewById(R.id.register_iconView);



        item = (Outlet) getIntent().getSerializableExtra(DATA_INTENT_OUTLET);

        mTypeTextView.setText(item.type.name);
        mAgentIdTextView.setText(Integer.toString(item.agentId));
        mOutletIdTextView.setText(Integer.toString(item.id));
        mNameEditText.setText(item.name);
        if(item.state)
            mIconView.setImageResource(item.type.onRessource);
        else
            mIconView.setImageResource(item.type.offRessource);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, HomeApplianceType.getHomeApplianceNames());
        mTypeSpinner.setAdapter(adapter);
        mTypeSpinner.setSelection(0);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.name = mNameEditText.getText().toString();
                item.type = HomeApplianceType.getType(mTypeSpinner.getSelectedItem().toString());
                presenter.registerOutlet(item);
            }
        });
    }

    @Override
    public void onRegisterSuccess(){
        runOnUiThread(() -> showMessage("Outlet Updated"));
        finish();
    }

    @Override
    public void onRegisterFailed(){
        runOnUiThread(() -> showMessage("Register failed"));
    }
}
