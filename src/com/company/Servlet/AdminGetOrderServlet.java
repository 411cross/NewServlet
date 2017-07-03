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
import java.util.HashMap;

/**
 * Created by derrickJ on 2017/7/2.
 */
public class AdminGetOrderServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        ArrayList<Order> orderList = new ArrayList<>();

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String orderSituation = jsonObject.getString("situation");

        try {
            orderList = OrderService.adminGetOrder(orderSituation);
            resp.setStatus(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String jsonString = gson.toJson(orderList);
        String response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";

        resp.getOutputStream().write(response.getBytes("utf-8"));

    }

}
