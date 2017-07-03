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

/**
 * Created by derrickJ on 2017/6/30.
 */
public class CreateOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String jsonString = jsonObject.getString("order");
        System.out.println(jsonString);
        Order order = gson.fromJson(jsonString, Order.class);
        System.out.println(order.getId());
        StringBuffer stringBuffer = new StringBuffer();

        try {
            boolean flag = OrderService.createOrder(order);
            if (flag) {
                resp.setStatus(200);
                stringBuffer.append("{\"statueCode\":\"200\",\"message\":\"成功\"}");
            } else {
                resp.setStatus(100);
                stringBuffer.append("{\"statueCode\":\"100\",\"message\":\"失败\"}");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));

    }

}
