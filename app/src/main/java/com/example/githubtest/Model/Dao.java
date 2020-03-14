package com.example.githubtest.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Dao {
    private final DataBaseHelper mDataBaseHelper;
    public Dao(Context context){
        mDataBaseHelper=new DataBaseHelper(context);

    }
    //增
    public void insert(String username,String password){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + Constants.TABLE_NAME + "(username,password) values(?,?)";
        db.execSQL(sql,new Object[]{username,md5(password)});
        db.close();

    }
    //删除
    public void delete(String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + Constants.TABLE_NAME + " where username=?";
        db.execSQL(sql,new Object[]{username});
        db.close();

    }
    //改
    public void updata(String username,String newpassword){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + Constants.TABLE_NAME + " set password=? where username=?";
        db.execSQL(sql,new Object[]{newpassword,md5(username)});
        db.close();

    }
    //登录查询
    public boolean loadQuery(String username,String password){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + Constants.TABLE_NAME + " where username=? and password=?";
        Cursor cursor=db.rawQuery(sql,new String[]{username,md5(password)});
        //Cursor cursor=db.query(Constants.TABLE_NAME,new String[]{"username","password"},"username=? and password=?",new String[]{username,password},null,null,null);
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //查看用户名是否存在
    public boolean usernameQuery(String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + Constants.TABLE_NAME + " where username=? ";
        Cursor cursor=db.rawQuery(sql,new String[]{username});
        //Cursor cursor=db.query(Constants.TABLE_NAME,new String[]{"username","password"},"username=? and password=?",new String[]{username,password},null,null,null);
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }

    //md5加密
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result="";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length()==1) {
                    temp = "0" + temp;
                }
                result+=temp;
            }
            return result;

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    };
}
