package com.example.duckiemusic.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.duckiemusic.R;
import com.example.duckiemusic.model.User;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {
    private EditText edUserNameC;
    private EditText edPasswordC;
    private EditText edConfirmPasswrodC;
    private Button btnRegister;
    private ImageButton imbBack;

    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private final Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        sharedPreferences=getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        edUserNameC=findViewById(R.id.edUserNameRe);
        edPasswordC=findViewById(R.id.edPasswordRe);
        edConfirmPasswrodC=findViewById(R.id.edt_confirm_password);
        btnRegister=findViewById(R.id.btRegister);
        imbBack=findViewById(R.id.imbBack);
        taosukien();
    }
    void taosukien()
    {
        btnRegister.setOnClickListener(v -> sukienRegister());
        imbBack.setOnClickListener(v -> finish());
    }
    void sukienRegister()
    {
        String username=edUserNameC.getText().toString().trim();
        String password=edPasswordC.getText().toString().trim();
        String confirmPassword=edConfirmPasswrodC.getText().toString().trim();
        boolean isValid=checkEmail(username)&&checkPassword(password,confirmPassword);
        if(isValid)
        {
            User userNew=new User();
            userNew.setUsername(username);
            userNew.setPassword(password);
            String userStr=gson.toJson(userNew);
            editor.putString(Utils.KEY_USER,userStr);
            editor.commit();
            Toast.makeText(RegisterActivity.this,"Đăng kí tài khoản thành công",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private boolean checkEmail(String email) {
        if(email.isEmpty())
        {
            edPasswordC.setError("Vui lòng nhập email");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password,String confirmPassword)
    {
        if(password.isEmpty())
        {
            edPasswordC.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if(!password.equals(confirmPassword))
        {
            edConfirmPasswrodC.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }

}