package com.example.tam_o.assign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private EditText email;
    private  EditText password;
    private DBHelper actionHelper;
    private  UserIni getUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionHelper = new DBHelper(this);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginemail);
        getUser = new UserIni();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gEmail =  email.getText().toString();
                String gPassword = password.getText().toString();
                Log.v("edittest", email.getText().toString() );
                if(validate(gEmail, gPassword)){
                    try {
                        getUser = actionHelper.getByUser(gEmail);
                    }catch (Exception err){
                        getUser = null;
                    }
                    if(getUser != null){
                        Log.i("user info", getUser.getId()+getUser.getFirstName()+"test"+gPassword);

                        if(gPassword.equals(getUser.getPassword())) loginModule(getUser);
                        else  Toast.makeText(MainActivity.this, "Wrong Password",
                                Toast.LENGTH_LONG).show();
                     } else {
                        Toast.makeText(MainActivity.this, "Wrong User",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill info",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpAct = new Intent(MainActivity.this, SignUpActivity.class );
                startActivity(signUpAct);
            }
        });
    }

    public boolean validate(String email, String password){
        return true;
    }

    public void loginModule (UserIni userPass) {
        Intent login = new Intent(MainActivity.this, DisplayActivity.class);
        login.putExtra("userId", userPass.getId());
        startActivity(login);
    }


}
