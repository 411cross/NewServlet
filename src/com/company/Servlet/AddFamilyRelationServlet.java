package com.company.Servlet;

import com.company.Service.GeneralService;
import com.company.Service.UserService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/6/30.
 */
public class AddFamilyRelationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Message = null;
        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String u_id = jsonObject.getString("u_id");
        int p_id = jsonObject.getInt("p_id");
        StringBuffer stringBuffer = new StringBuffer();
        String response;

        try {
            Message = UserService.addFamilyRelationship(u_id,p_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(Message.isEmpty()){
            resp.setStatus(200);
            stringBuffer.append(Message);
        }else {
            resp.setStatus(201);
            stringBuffer.append("{\"statueCode\":\"201\",\"message\":\"失败\"}");
        }

        resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));

    }

}
