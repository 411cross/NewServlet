package com.company.Util;

import com.company.DAO.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by derrickJ on 2017/7/1.
 */
public class DatabaseOperation {

    Connection conn = DBconnect.getConn();

    public int insert(String tableName, ArrayList<Tuple> keyAndValue) throws SQLException {

        PreparedStatement prestate;

        String sql = "insert into `" + tableName + "` ";
        String keyPart = "(";
        String valuePart = "values(";
        String key;
        String value;
        int dataType;

        for (int i = 0; i < keyAndValue.size(); i++) {

            key = keyAndValue.get(i).getKey();

            if (i == 0) {
                keyPart = keyPart + "`" + key + "`";
                valuePart = valuePart + "?";
            } else {
                keyPart = keyPart + ", `" + key + "`";
                valuePart = valuePart + ", ?";
            }

        }

        keyPart = keyPart + ") ";
        valuePart = valuePart + ")";

        sql = sql + keyPart + valuePart;
        prestate = conn.prepareStatement(sql);


//        prestate.setString(1, nurse.getNurseName());
//        prestate.setInt(2, nurse.getNurseSex());

        for (int j = 0; j < keyAndValue.size(); j++) {

            dataType = keyAndValue.get(j).getDataType();
            value = keyAndValue.get(j).getValue();
            if (dataType == 0) { //0表示int值
                prestate.setInt(j, Integer.parseInt(value));
            } else {
                prestate.setString(j, value);
            }

        }

        int row = prestate.executeUpdate();

        if (row == 0) {
            throw new SQLException();
        }

        String getLastIDSql = "select LAST_INSERT_ID()";
        prestate = conn.prepareStatement(getLastIDSql);
        ResultSet result = prestate.executeQuery();

        return result.getInt("LAST_INSERT_ID()");

    }

//     public boolean select(String tableName, ArrayList<Tuple> keyAndValue, ArrayList<String> operation) {
//
//         boolean flag = true;
//
//
//
//         return flag;
//
//     }

}
