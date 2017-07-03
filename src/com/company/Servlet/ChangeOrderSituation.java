package com.company.Servlet;

import com.company.Entity.User;
import com.company.Service.GeneralService;
import com.company.Service.OrderService;
import com.company.Service.UserService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by derrickJ on 2017/7/3.
 */
public class ChangeOrderSituation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        int id = jsonObject.getInt("id");
        int situation = jsonObject.getInt("situation");

        StringBuffer stringBuffer = new StringBuffer();
        try {
            if(OrderService.changeOrderSituation(id, situation)){
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
