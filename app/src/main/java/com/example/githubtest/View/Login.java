package com.example.githubtest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.githubtest.Controller.C_Login;
import com.example.githubtest.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button mButton1,mButton2;
    EditText mEditText1,mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        mButton1=findViewById(R.id.login_btn1);
        mButton2=findViewById(R.id.login_btn2);
        //密码
        mEditText1=findViewById(R.id.login_edit1);
        //账号
        mEditText2=findViewById(R.id.login_edit2);
    }
    private void initEvent(){
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //isUsernameExit判断用户名是否存在，isPasswordRight判断密码是否正确，属于Controller中定义的方法
        switch (v.getId()){
            case R.id.login_btn1:
                Intent intent=new Intent(this,Register.class);
                startActivity(intent);
                break;
            case R.id.login_btn2:
                if (mEditText1.getText().toString().equals("") || mEditText2.getText().toString().equals("")){
                    Toast.makeText(this,"账号或密码为空",Toast.LENGTH_SHORT).show();

                }else{
                    //判断用户是否存在
                    if(C_Login.c_login().isUsernameExit(mEditText2.getText().toString(),this)){
                        //判断密码是否正确
                        if(C_Login.c_login().isPasswordRight(mEditText2.getText().toString(),mEditText1.getText().toString(),this)){
                            intent = new Intent(this,MainInterface.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(this,"密码不正确",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"账号不存在",Toast.LENGTH_SHORT).show();
                        Log.v("账号",mEditText1.getText().toString());
                    }
                }
                break;
            default:
                break;
        }
    }
}
