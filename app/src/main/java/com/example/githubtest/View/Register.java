package com.example.githubtest.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.githubtest.Controller.C_Login;
import com.example.githubtest.Model.Dao;
import com.example.githubtest.R;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText1,mEditText2,mEditText3;
    Button mButton1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        //密码
        mEditText1=findViewById(R.id.register_edit1);
        //账号
        mEditText2=findViewById(R.id.register_edit2);
        //确认密码
        mEditText3=findViewById(R.id.register_edit3);

        mButton1=findViewById(R.id.register_btn1);

    }
    private void initEvent(){
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn1:
                if (mEditText1.getText().toString().equals("") || mEditText2.getText().toString().equals("") || mEditText3.getText().toString().equals("")){
                    Toast.makeText(this,"账号或密码为空",Toast.LENGTH_SHORT).show();

                }else{
                    if(!mEditText1.getText().toString().equals(mEditText3.getText().toString())){
                        Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }else{
                        if(C_Login.c_login().isUsernameExit(mEditText2.getText().toString(),this)){
                            Toast.makeText(this,"账号已经存在",Toast.LENGTH_SHORT).show();
                        }else {
                            Dao mDao1=new Dao(this);
                            mDao1.insert(mEditText2.getText().toString(),mEditText1.getText().toString());
                            Intent intent = new Intent(this,Login.class);
                            startActivity(intent);
                        }
                    }

                }
                break;
            default:
                break;
        }

    }
}
