package com.example.duckiemusic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duckiemusic.MainActivity;
import com.example.duckiemusic.R;
import com.example.duckiemusic.model.User;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button btLogin,btRegister;
    EditText edUserNameC,edPasswordC;
    SharedPreferences.Editor editor;
    private final Gson gson =new Gson();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        anhxa();
        sharedPreferences=getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        taosukien();

    }
    private void taosukien()
    {
        btLogin.setOnClickListener(v -> checkLogin());
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void anhxa()
    {
        btLogin=findViewById(R.id.btLogin);
        btRegister=findViewById(R.id.btRegister);
        edUserNameC=findViewById(R.id.edUserName);
        edPasswordC=findViewById(R.id.edPassword);
    }
    private void checkLogin()
    {
        String userPref=sharedPreferences.getString(Utils.KEY_USER,null);
        User user=gson.fromJson(userPref,User.class);
        if(user==null)
        {
            return;
        }
        boolean isValid=edUserNameC.getText().toString().trim().equals(user.getUsername()) && edPasswordC.getText().toString().trim().equals(user.getPassword());
        if(isValid)
        {
            Intent intent=new Intent(this,MainActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable(Utils.KEY_USER_PROFILE,user);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}