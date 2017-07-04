package com.company.Servlet;

import com.company.Service.GeneralService;
import com.company.Service.NurseService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by derrickJ on 2017/6/30.
 */
public class DeleteNurseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        int id = jsonObject.getInt("id");

        StringBuffer stringBuffer = new StringBuffer();

        try {

            if (NurseService.deleteNurse(id)) {
                resp.setStatus(200);
                stringBuffer.append("{\"statueCode\":\"200\",\"message\":\"成功\"}");
            } else {
                resp.setStatus(201);
                stringBuffer.append("{\"statueCode\":\"201\",\"message\":\"失败\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));

    }

}
