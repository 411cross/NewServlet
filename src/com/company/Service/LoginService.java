package com.company.Service;

import com.company.DAO.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/6/29.
 */
public class LoginService {
    static public String userLogin(String id, String password){

        String flag = "";

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_user where id =?  and password = ?";
        try{
            prestate = conn.prepareStatement(sql);

            prestate.setString(1, id);
            prestate.setString(2, password);

            ResultSet result = prestate.executeQuery();

            while(result.next()){

                flag = result.getString("id");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return flag;
    }


    static public boolean administratorLogin(int id ,String password){
        boolean flag = false;
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_administrator where id =?  and password = ?";

        try{
            prestate = conn.prepareStatement(sql);
            prestate.setInt(1, id);
            prestate.setString(2, password);

            ResultSet result = prestate.executeQuery();

            while(result.next()){
                System.out.println("登录成功！");

                flag = true;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return flag;

    }


}
