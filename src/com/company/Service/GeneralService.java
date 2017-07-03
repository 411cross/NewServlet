package com.company.Service;

import com.company.Entity.User;
import com.company.DAO.DBconnect;
import com.company.Entity.Nurse;
import com.company.Entity.Patient;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by derrickJ on 2017/6/29.
 */

/**
 * 改继承
 */

public class GeneralService {

    static public User getUser(String userID) {

        User user = new User();

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_user where id =?";

        try {
            prestate = conn.prepareStatement(sql);

            prestate.setString(1, userID);

            ResultSet result = prestate.executeQuery();

            while (result.next()) {
//
                String id = result.getString("id");
                String password = result.getString("password");
                String name = result.getString("name");
                String avatar = result.getString("avatar");

                user.setId(id);
                user.setPassword(password);
                user.setName(name);
                user.setAvatar(avatar);

                System.out.println(user.getId() + " " + user.getName() + " " + user.getPassword());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    static public Patient getPatient(int patientID) {

        Patient patient = new Patient(); //不能声明的时候赋值null，会爆空指针错误

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_patient where id =?";

        try {
            prestate = conn.prepareStatement(sql);

            prestate.setInt(1, patientID);

            ResultSet result = prestate.executeQuery();

            while (result.next()) {
//
                int id = result.getInt("id");
                String bedNumber = result.getString("bed_number");
                String name = result.getString("name");
                int sex = result.getInt("sex");
                String disease = result.getString("disease");
                String contactName = result.getString("contact_name");
                String contactPhone = result.getString("contact_phone");

                patient.setId(id);
                patient.setBedNumber(bedNumber);
                patient.setName(name);
                patient.setSex(sex);
                patient.setDisease(disease);
                patient.setContactName(contactName);
                patient.setContactPhone(contactPhone);

                System.out.println(patient.getId() + " " + patient.getName());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return patient;
    }

    static public Nurse getNurse(int nurseID) {

        Nurse nurse = new Nurse();

        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        String sql = "select * from app_nurse where id =?";
        try {
            prestate = conn.prepareStatement(sql);

            prestate.setInt(1, nurseID);

            ResultSet result = prestate.executeQuery();

            while (result.next()) {
//
                int id = result.getInt("id");
                String name = result.getString("name");
                int sex = result.getInt("sex");
                int age = result.getInt("age");
                int workAge = result.getInt("work_age");
                int price = result.getInt("price");
                int evaluation = result.getInt("evaluation");
                String phone = result.getString("phone");
                int height = result.getInt("height");
                int weight = result.getInt("weight");
                String bloodType = result.getString("blood_type");
                String nation = result.getString("nation");
                String identity = result.getString("identity");
                String constellation = result.getString("constellation");
                String animal = result.getString("animal");
                String description = result.getString("description");
                String area = result.getString("area");

                nurse.setNurseId(id);
                nurse.setNurseName(name);
                nurse.setNurseSex(sex);
                nurse.setNurseAge(age);
                nurse.setNurseWorkAge(workAge);
                nurse.setNursePrice(price);
                nurse.setNurseEvaluate(evaluation);
                nurse.setNursePhone(phone);
                nurse.setNurseHeight(height);
                nurse.setNurseWeight(weight);
                nurse.setNurseBloodType(bloodType);
                nurse.setNurseNation(nation);
                nurse.setNurseIdentity(identity);
                nurse.setNurseConstellation(constellation);
                nurse.setNurseAnimal(animal);
                nurse.setNurseDescription(description);
                nurse.setNurseArea(area);

                System.out.println(nurse.getNurseId() + " " + nurse.getNurseName() + " " + nurse.getNurseEvaluate());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nurse;
    }

    public static JSONObject toJsonObject(HttpServletRequest request) throws IOException, UnsupportedEncodingException {

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //将json字符串转换为json对象
        JSONObject jsonObject=JSONObject.fromObject(sb.toString());
        return jsonObject;

    }
}
