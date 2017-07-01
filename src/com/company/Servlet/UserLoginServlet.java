package com.company.Servlet;

/**
 * Created by Administrator on 2017/6/29.
 */
import com.company.Entity.User;
import com.company.Service.GeneralService;
import com.company.Service.LoginService;
import com.google.gson.Gson;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        System.out.println(jsonObject.toString());
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");
        User user;
        Gson gson = new Gson();

        StringBuffer stringBuffer = new StringBuffer();

//        PrintWriter out = resp.getWriter();

        if(!LoginService.userLogin(id, password).equals("")){
            resp.setStatus(200);
            user = GeneralService.getUser(LoginService.userLogin(id, password));
            String jsonString = gson.toJson(user);
            String response = "{\"statueCode\":\"200\",\"data\":" + jsonString + "}";
            resp.getOutputStream().write(response.getBytes("utf-8"));
//            out.write("{\"statueCode\":\"200\",\"data\":" + jsonString + "}");
        }else{
            resp.setStatus(100);
            stringBuffer.append("{\"statueCode\":\"100\",\"message\":\"失败\"}");
            resp.getOutputStream().write(stringBuffer.toString().getBytes("utf-8"));
            req.setAttribute("result", "failed");
        }

//        out.flush();
//        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req,resp);
    }

}
