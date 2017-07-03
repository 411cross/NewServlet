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
        int id = jsonObject.getInt("nurseId");
        String name = jsonObject.getString("nurseName");
        int sex = jsonObject.getInt("nurseSex");
        int age = jsonObject.getInt("nurseAge");
        int workAge = jsonObject.getInt("nurseWorkAge");
        int price = jsonObject.getInt("nursePrice");
        int evalution = jsonObject.getInt("nurseEvaluate");
        String phone = jsonObject.getString("nursePhone");
        int height = jsonObject.getInt("nurseHeight");
        int weight = jsonObject.getInt("nurseWeight");
        String bloodType = jsonObject.getString("nurseBloodType");
        String nation = jsonObject.getString("nurseNation");
        String identity = jsonObject.getString("nurseIdentity");
        String constellation = jsonObject.getString("nurseConstellation");
        String animal = jsonObject.getString("nurseAnimal");
        String description = jsonObject.getString("nurseDescription");
        String area = jsonObject.getString("nurseArea");
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
                stringBuffer.append("{\"statueCode\":\"200\",\"message\":\"成功\"}");
            }else{
                stringBuffer.append("{\"statueCode\":\"100\",\"message\":\"失败\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));


    }
}
