package com.company.Servlet;

import com.company.Entity.Nurse;
import com.company.Service.NurseService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/30.
 */
public class GetNurseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        ArrayList<Nurse> nurseList = new ArrayList<>();
        String response;

        try {
            nurseList = NurseService.getNurseList();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(nurseList.size() != 0){
            resp.setStatus(200);
            String jsonString = gson.toJson(nurseList);
            response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";
        }else {
            resp.setStatus(201);
            response = "{\"statueCode\":\"201\",\"message\":\"失败\"}";
        }

        resp.getOutputStream().write(response.getBytes("utf-8"));

    }

}
