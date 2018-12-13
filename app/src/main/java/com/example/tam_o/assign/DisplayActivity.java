package com.example.tam_o.assign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class DisplayActivity extends AppCompatActivity {
    private DBHelper actionHelper;
    private TextView email;
    private TextView firstname;
    private TextView lastname;
    private TextView privilege;
    private TextView admin;
    private Button editButton;
    private Button deleteButton;
    private UserIni user;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        actionHelper = new DBHelper(this);
        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt("userId");
        Log.i("ID",valueOf(userId) );
        email = (TextView) findViewById(R.id.temail);
        firstname = (TextView) findViewById(R.id.tfname);
        lastname = (TextView) findViewById(R.id.tlname);
        privilege = (TextView) findViewById(R.id.tprivilege);
        admin = (TextView) findViewById(R.id.admin);
        editButton = (Button) findViewById(R.id.editBut);
        deleteButton = (Button) findViewById(R.id.deleteBut);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edittent = new Intent(DisplayActivity.this, Update.class);
                edittent.putExtra("userId", userId);
                edittent.putExtra("firstname", firstname.getText().toString());
                edittent.putExtra("lastname", lastname.getText().toString());
                startActivity(edittent);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionHelper.deleteUserById(userId);
                finish();
            }
        });
        initTextView(userId);
    }

    public void initTextView(int id){
        user = actionHelper.getById(id);
        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        email.setText(user.getEmail());
        privilege.setText(user.getPrivilege());
        if(user.getPrivilege().equals("admin")) admin.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTextView(userId);
    }
}
