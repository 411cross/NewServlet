package com.company.Service;

import com.company.DAO.DBconnect;
import com.company.Entity.Nurse;
import com.company.Entity.Order;
import com.company.Entity.Patient;
import com.company.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/29.
 */
public class OrderService {

    static public ArrayList<Order> getOrder(String userId, String orderSituation) throws SQLException {

        ArrayList<Order> orderList = new ArrayList<>();
        Order tempOrder = new Order();
        User tempUser;
        Nurse tempNurse;
        Patient tempPatient;

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        if (orderSituation.equals("new")) {
            String sql = "select * from app_order where u_id =? and (situation = 0 or situation = 1 or situation = 4 or situation = 5)";
            prestate = conn.prepareStatement(sql);
        } else {
            String sql = "select * from app_order where u_id =? and (situation = 2 or situation = 3)";
            prestate = conn.prepareStatement(sql);
        }

        prestate.setString(1, userId);
        ResultSet result = prestate.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            int totalPrice = result.getInt("total_price");
            String createTime = result.getString("create_time");
            String serviceTime = result.getString("service_time");
            int type = result.getInt("type");
            int situation = result.getInt("situation");
            int choseNurse = result.getInt("chose_nurse");
            int nurseID = result.getInt("n_id");
            int patientID = result.getInt("p_id");
            System.out.println(id);
            System.out.println(nurseID);
            System.out.println(patientID);

            tempUser = GeneralService.getUser(userId);
            tempNurse = GeneralService.getNurse(nurseID);
            tempPatient = GeneralService.getPatient(patientID);

            tempOrder.setId(id);
            tempOrder.setTotalPrice(totalPrice);
            tempOrder.setCreateTime(createTime);
            tempOrder.setServiceTime(serviceTime);
            tempOrder.setType(type);
            tempOrder.setSituation(situation);
            tempOrder.setChoseNurse(choseNurse);
            tempOrder.setUser(tempUser);
            tempOrder.setNurse(tempNurse);
            tempOrder.setPatient(tempPatient);

            orderList.add(tempOrder);

        }

        return orderList;
    }

    static public boolean createOrder(Order order) throws SQLException{

        boolean flag = false;

        int id = order.getId();
        int totalPrice = order.getTotalPrice();
        String createTime = order.getCreateTime();
        String serviceTime = order.getServiceTime();
        int type = order.getType();
        int situation = order.getSituation();
        int choseNurse = order.getChoseNurse();
        int nurseID = order.getNurse().getNurseId();
        int patientID = order.getPatient().getId();
        String userID = order.getUser().getId();


        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;
        String sql = "insert into app_order (total_price, create_time, service_time, " +
                "type, situation, chose_nurse, n_id, p_id, u_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prestate = conn.prepareStatement(sql);
        prestate.setInt(1, totalPrice);
        prestate.setString(2, createTime);
        prestate.setString(3, serviceTime);
        prestate.setInt(4, type);
        prestate.setInt(5, situation);
        prestate.setInt(6, choseNurse);
        prestate.setInt(7, nurseID);
        prestate.setInt(8, patientID);
        prestate.setString(9, userID);

        int result = prestate.executeUpdate();
        if (result == 1){
            flag = true;
        }
        System.out.println(flag);

        return flag;

    }

    static public ArrayList<Order> adminGetOrder(String orderSituation) throws SQLException {

        ArrayList<Order> orderList = new ArrayList<>();

        User tempUser;
        Nurse tempNurse;
        Patient tempPatient;

        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        if (orderSituation.equals("new")) {
            String sql = "select * from app_order where situation = 0 or situation = 1 or situation = 4 or situation = 5";
            prestate = conn.prepareStatement(sql);
        } else {
            String sql = "select * from app_order where situation = 2 or situation = 3";
            prestate = conn.prepareStatement(sql);
        }

        ResultSet result = prestate.executeQuery();

        while (result.next()) {

            int id = result.getInt("id");
            int totalPrice = result.getInt("total_price");
            String createTime = result.getString("create_time");
            String serviceTime = result.getString("service_time");
            int type = result.getInt("type");
            int situation = result.getInt("situation");
            int choseNurse = result.getInt("chose_nurse");
            int nurseID = result.getInt("n_id");
            int patientID = result.getInt("p_id");
            String userID = result.getString("u_id");

            Order tempOrder = new Order();

            tempUser = GeneralService.getUser(userID);
            tempNurse = GeneralService.getNurse(nurseID);
            tempPatient = GeneralService.getPatient(patientID);

            tempOrder.setId(id);
            tempOrder.setTotalPrice(totalPrice);
            tempOrder.setCreateTime(createTime);
            tempOrder.setServiceTime(serviceTime);
            tempOrder.setType(type);
            tempOrder.setSituation(situation);
            tempOrder.setChoseNurse(choseNurse);
            tempOrder.setUser(tempUser);
            tempOrder.setNurse(tempNurse);
            tempOrder.setPatient(tempPatient);

            orderList.add(tempOrder);
        }

        System.out.println(orderList.get(0).getId());
        System.out.println(orderList.get(1).getId());

        return orderList;
    }

    static public boolean changeOrderSituation(int id, int situation) throws SQLException {

        boolean flag = false;
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "update app_order set situation=? where id =?";
        prestate = (PreparedStatement) conn.prepareStatement(sql);
        prestate.setInt(1, situation);
        prestate.setInt(2, id);

        int i = prestate.executeUpdate();//返回更新数目的条数
        if (i == 1) flag = true;
        else flag = false;

        return flag;

    }

    static public boolean chooseNurseForPatient(int id, int nurseId) throws SQLException {

        boolean flag = false;
        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "update app_order set n_id=? where id =?";
        prestate = (PreparedStatement) conn.prepareStatement(sql);
        prestate.setInt(1, nurseId);
        prestate.setInt(2, id);

        int i = prestate.executeUpdate();//返回更新数目的条数
        if (i == 1) flag = true;
        else flag = false;

        return flag;

    }


}
