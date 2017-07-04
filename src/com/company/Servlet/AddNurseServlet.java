package com.company.Servlet;

import com.company.Entity.Nurse;
import com.company.Service.GeneralService;
import com.company.Service.NurseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */
public class AddNurseServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        System.out.println(jsonObject.toString());
        int id = jsonObject.getInt("nurseId");
        System.out.println("ID"+id);
        String name = jsonObject.getString("nurseName");
        System.out.println("NAME"+name);
        int sex = jsonObject.getInt("nurseSex");
        System.out.println("SEX"+sex);
        int age = jsonObject.getInt("nurseAge");
        System.out.println("AGE"+age);
        int workAge = jsonObject.getInt("nurseWorkAge");
        System.out.println("WA"+workAge);
        int price = jsonObject.getInt("nursePrice");
        System.out.println("P"+price);
        int evalution = jsonObject.getInt("nurseEvaluate");
        System.out.println("EV"+evalution);
        String phone = jsonObject.getString("nursePhone");
        System.out.println("PH"+phone);
        int height = jsonObject.getInt("nurseHeight");
        System.out.println("H"+height);
        int weight = jsonObject.getInt("nurseWeight");
        System.out.println("W"+weight);
        String bloodType = jsonObject.getString("nurseBloodType");
        System.out.println("blo"+bloodType);
        String nation = jsonObject.getString("nurseNation");
        System.out.println(nation);
        String identity = jsonObject.getString("nurseIdentity");
        System.out.println(identity);
        String constellation = jsonObject.getString("nurseConstellation");
        System.out.println(constellation);
        String animal = jsonObject.getString("nurseAnimal");
        System.out.println(animal);
        String description = jsonObject.getString("nurseDescription");
        System.out.println(description);
        String area = jsonObject.getString("nurseArea");
        System.out.println(area);
        ArrayList<Integer> protextAreaList =new ArrayList<>();
        JSONArray protextArea = jsonObject.getJSONArray("nurseProtectArea");
        for(int i=0;i<protextArea.size();i++){
            protextAreaList.add(protextArea.getInt(i));
        }
        Nurse nurse = new Nurse(name,sex,age,workAge,area,evalution,price,protextAreaList,height,weight,bloodType,nation,identity,constellation,animal,description,phone);
        nurse.setNurseId(id);
        StringBuffer stringBuffer = new StringBuffer();

        try {
            if(NurseService.addNurse(nurse)){
                resp.setStatus(200);
                stringBuffer.append("{\"statueCode\":\"200\",\"message\":\"成功\"}");
            }else{
                resp.setStatus(201);
                stringBuffer.append("{\"statueCode\":\"100\",\"message\":\"失败\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));


    }
}
