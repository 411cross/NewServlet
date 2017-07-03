package com.company.Servlet;

import com.company.Entity.Nurse;
import com.company.Service.GeneralService;
import com.company.Service.NurseService;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/7/3.
 */
public class GetNurseByTypeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        ArrayList<Nurse> nurseList = new ArrayList<>();

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        System.out.println(jsonObject.toString());
        int area = jsonObject.getInt("area");

        try {
            nurseList = NurseService.searchTypeNurse(area);
            resp.setStatus(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String jsonString = gson.toJson(nurseList);
        String response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";

        resp.getOutputStream().write(response.getBytes("utf-8"));

    }

}
