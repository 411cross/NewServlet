package com.company.Servlet;

import com.company.Service.GeneralService;
import com.company.Service.LoginService;
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
 * Created by Administrator on 2017/6/29.
 */
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        int id = jsonObject.getInt("id");
        String password = jsonObject.getString("password");
        Gson gson = new Gson();
        ArrayList<String> nurseNameList = new ArrayList<>();

        StringBuffer stringBuffer = new StringBuffer();

        try {
            if (LoginService.administratorLogin(id, password)) {

                nurseNameList = NurseService.getNurseName();

                String jsonString = gson.toJson(nurseNameList);
                String response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";
                resp.setStatus(200);
                resp.getOutputStream().write(response.getBytes("utf-8"));
            } else {
                resp.setStatus(201);
                stringBuffer.append("{\"statueCode\":\"201\",\"message\":\"失败\"}");
                resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
