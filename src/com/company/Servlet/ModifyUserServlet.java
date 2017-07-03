package com.company.Servlet;

import com.company.Entity.User;
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
public class ModifyUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = GeneralService.toJsonObject(req);
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");
        String name =jsonObject.getString("name");
        String avatar =jsonObject.getString("avatar");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setAvatar(avatar);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if(UserService.modifyUserInfomation(user)){
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
