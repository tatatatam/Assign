package com.example.tam_o.assign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private Button update;
    private int userId;
    private String sFname;
    private String sLname;
    private DBHelper actionHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle bundle = getIntent().getExtras();
        actionHelper = new DBHelper(this);
        userId = bundle.getInt("userId");
        sFname = bundle.getString("firstname");
        sLname = bundle.getString("lastname");
        update = (Button) findViewById(R.id.updateButton);
        firstname = (EditText) findViewById(R.id.efname);
        lastname = (EditText) findViewById(R.id.elname);
        firstname.setText(sFname);
        lastname.setText(sLname);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserIni user = new UserIni();
                user.setFirstName(firstname.getText().toString());
                user.setLastName(lastname.getText().toString());
                user.setId(userId);
                actionHelper.updateUserById(user);
                finish();
            }
        });

    }
}
