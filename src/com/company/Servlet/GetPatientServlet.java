package com.company.Servlet;

import com.company.Entity.Patient;
import com.company.Service.GeneralService;
import com.company.Service.UserService;
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
 * Created by derrickJ on 2017/6/30.
 */
public class GetPatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        ArrayList<Patient> patientList = new ArrayList<>();

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String userId = jsonObject.getString("id");

        try {
            patientList = UserService.getRelatedPatient(userId);
            resp.setStatus(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String jsonString = gson.toJson(patientList);
        String response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";

        resp.getOutputStream().write(response.getBytes("utf-8"));

    }

}
