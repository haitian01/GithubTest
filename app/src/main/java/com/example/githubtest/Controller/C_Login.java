package com.example.githubtest.Controller;

import android.content.Context;

import com.example.githubtest.Model.Dao;
import com.example.githubtest.View.Login;

public class C_Login {

    //饿汉式单例模式
    private static C_Login s=new C_Login();
    private C_Login(){}
    public static C_Login c_login(){
        return s;
    }
    //判断用户名是否存在
    public boolean isUsernameExit(String username,Context context){
        Dao mDao=new Dao(context);
        if(mDao.usernameQuery(username)){
            return true;
        }
        return false;
    }
    //判断密码是否正确
    public boolean isPasswordRight(String username, String password, Context context){
        Dao mDao=new Dao(context);
        if(mDao.loadQuery(username,password)){
            return true;
        }
        return false;
    }
}
//加点东西进行测试

