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
            String sql = "select * from app_order where u_id =? and (situtation = 0 or situation = 1 or situation = 4 or situation = 5)";
            prestate = conn.prepareStatement(sql);
        } else {
            String sql = "select * from app_order where u_id =? and (situtation = 2 or situation = 3)";
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
        String sql = "insert into app_order (id, total_price, create_time, service_time, " +
                "type, situation, chose_nurse, n_id, p_id, u_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prestate = conn.prepareStatement(sql);
        prestate.setInt(1, id);
        prestate.setInt(2, totalPrice);
        prestate.setString(3, createTime);
        prestate.setString(4, serviceTime);
        prestate.setInt(5, type);
        prestate.setInt(6, situation);
        prestate.setInt(7, choseNurse);
        prestate.setInt(8, nurseID);
        prestate.setInt(9, patientID);
        prestate.setString(10, userID);

        int result = prestate.executeUpdate();

        while(result != 0){
            flag = true;
        }

        return flag;

    }

    static public ArrayList<Order> adminGetOrder(String orderSituation) throws SQLException {

        ArrayList<Order> orderList = new ArrayList<>();
        Order tempOrder = new Order();
        User tempUser;
        Nurse tempNurse;
        Patient tempPatient;

        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        if (orderSituation.equals("new")) {
            String sql = "select * from app_order where situtation = 0 or situation = 1 or situation = 4 or situation = 5";
            prestate = conn.prepareStatement(sql);
        } else {
            String sql = "select * from app_order where situtation = 2 or situation = 3";
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

        return orderList;
    }


}
