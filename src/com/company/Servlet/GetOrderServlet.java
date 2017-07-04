package com.company.Servlet;

import com.company.Entity.Order;
import com.company.Service.GeneralService;
import com.company.Service.OrderService;
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
public class GetOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        ArrayList<Order> orderList = new ArrayList<>();

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String userId = jsonObject.getString("id");
        String orderSituation = jsonObject.getString("situation");
        String response;

        try {
            orderList = OrderService.getOrder(userId, orderSituation);
            resp.setStatus(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(orderList.size() != 0){
            resp.setStatus(200);
            String jsonString = gson.toJson(orderList);
            response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";
            resp.getOutputStream().write(response.getBytes("utf-8"));
        }else {
            resp.setStatus(201);
            response = "{\"statueCode\":\"201\",\"message\":\"失败\"}";
            resp.getOutputStream().write(response.getBytes("utf-8"));
        }

        resp.getOutputStream().write(response.getBytes("utf-8"));

    }

}
