/**
 * Activity for view that presents the Socket register
 * @author  Martin J. J.
 * @version 1.0
 * @since   11/20/2019
 */
package com.xpower.xhomeremote.ui.socketregister;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.SocketDTO;
import com.xpower.xhomeremote.ui.base.BaseActivity;

public class SocketRegisterActivity extends BaseActivity implements ISocketRegisterView {
    public static final String DATA_INTENT_SOCKET = "DATA_INTENT_SOCKET";
    TextView typeTextView, agentIdTextView, socketIdTextView;
    EditText nameEditText;
    Spinner typeSpinner;
    Button button;

    private SocketDTO item;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_register);

        typeTextView = findViewById(R.id.register_Type);
        agentIdTextView = findViewById(R.id.register_AgentID);
        socketIdTextView = findViewById(R.id.register_SocketID);
        nameEditText = findViewById(R.id.register_Name_input);
        typeSpinner = findViewById(R.id.register_Type_spinner);
        button = findViewById(R.id.register_button);

        item = (SocketDTO) getIntent().getSerializableExtra(DATA_INTENT_SOCKET);

        typeTextView.setText(item.type.name());
        agentIdTextView.setText(Integer.toString(item.agentId));
        socketIdTextView.setText(Integer.toString(item.id));
        nameEditText.setText(item.name);

        ArrayAdapter<HomeApplianceType> adapter = new ArrayAdapter<HomeApplianceType>(this, android.R.layout.simple_list_item_1, HomeApplianceType.values());
        typeSpinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void registerSuccesfull(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMessage("Socket Updated");
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
