package com.example.tam_o.assign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private Button signup;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText privilege;
    private DBHelper actionHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutsignup);
        actionHelper = new DBHelper(this);
        signup = (Button) findViewById(R.id.signupBut);
        firstName = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.last);
        email = (EditText) findViewById(R.id.email);
        privilege = (EditText) findViewById(R.id.privilege);
        password = (EditText) findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserIni user = new UserIni();
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setPrivilege(privilege.getText().toString());

                actionHelper.insertUser(user);
                finish();
            }
        });

    }
}
