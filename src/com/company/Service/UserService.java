package com.company.Service;

import com.company.DAO.DBconnect;
import com.company.Entity.Patient;
import com.company.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */
public class UserService {

    /*
    * 登录
    * */

    static public boolean register(String id, String name, String pass) throws SQLException {
        boolean flag = false;
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "insert into app_user(id,password,name) values(?,?,?)";
        prestate = (PreparedStatement) conn.prepareStatement(sql);
        prestate.setString(1, id);
        prestate.setString(2, pass);
        prestate.setString(3, name);

        int i = prestate.executeUpdate();   //返回更新数目的条数
        if (i == 1) flag = true;
        else flag = false;

        return flag;
    }

    /*用户修改个人信息*/

    static public boolean modifyUserInfomation(User user) throws SQLException {


        boolean flag = false;
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "update app_user set passord=?,name=?,avatar=? where id =?";
        prestate = (PreparedStatement) conn.prepareStatement(sql);
        prestate.setString(1, user.getPassword());
        prestate.setString(2, user.getName());
        prestate.setString(3, user.getAvatar());
        prestate.setString(4, user.getId());

        int i = prestate.executeUpdate();   //返回更新数目的条数
        if (i == 1) flag = true;
        else flag = false;

        return flag;

    }

    static public ArrayList<Patient> getRelatedPatient(String id) throws SQLException {

        Patient tempPatient = null;
        ArrayList<Patient> patientList = new ArrayList<>();

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_relation where u_id =?";

        prestate = conn.prepareStatement(sql);

        prestate.setString(1, id);

        ResultSet result = prestate.executeQuery();

        while (result.next()) {

            int patientID = result.getInt("p_id");
            tempPatient = GeneralService.getPatient(patientID);
            patientList.add(tempPatient);

        }

        return patientList;

    }

    /*增加家属关系
    * 输入 用户ID 和 病人ID
    * 错误状态码 400 ： 已有家属关系，不用添加
    *            401 ： 添加出错
    * 正确：200
    * */

    static public String addFamilyRelationship(String userID,int patientID) throws SQLException {

        String statueCode = "";
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;
        String sql = "select * from app_relation where u_id=? and p_id=?";
        String sql1 = "insert into app_relation(u_id,p_id) values(?,?)";
        prestate = (PreparedStatement) conn.prepareStatement(sql);
        prestate.setString(1,userID);
        prestate.setInt(2,patientID);


        ResultSet result = prestate.executeQuery();
        if(result.next()){
            statueCode="{\"StatueCode\":\"400\",\"Message\":\"出错,已有家属关系.\"}";
            return statueCode;
        }
        else{
            prestate = (PreparedStatement) conn.prepareStatement(sql1);
            prestate.setString(1,userID);
            prestate.setInt(2,patientID);
            int i = prestate.executeUpdate();
            if(i==1){
                statueCode="{\"StatueCode\":\"200\",\"Message\":\"成功\"}";
                return statueCode;
            }else{
                statueCode="{\"StatueCode\":\"401\",\"Message\":\"添加出错\"}";
                return statueCode;
            }
        }



    }


}
