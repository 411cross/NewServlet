package com.company.Service;

import com.company.DAO.DBconnect;
import com.company.Entity.Nurse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/29.
 */
public class NurseService {
    static public ArrayList<Nurse> filterNurse(int filter, int position) throws SQLException {

        ArrayList<Nurse> NurseList = new ArrayList<Nurse>();


        Connection conn = DBconnect.getConn();

        PreparedStatement prestate;

        switch (filter) {
            case 1:
                String sql = "select n_id from app_range where type =? ";

                prestate = conn.prepareStatement(sql);

                prestate.setInt(1, position);

                ResultSet result = prestate.executeQuery();

                while (result.next()) {
                    NurseList.add(GeneralService.getNurse(result.getInt("n_id")));
                }
                break;
            case 2:
                switch (position) {
                    case 1:
                        String sql1 = "select id from app_nurse where work_age<=3 and work>=1 ";
                        prestate = conn.prepareStatement(sql1);
                        ResultSet result1 = prestate.executeQuery();
                        while (result1.next()) {
                            NurseList.add(GeneralService.getNurse(result1.getInt("n_id")));
                        }
                        break;
                    case 2:
                        String sql2 = "select id from app_nurse where work_age<=6 and work>=4 ";
                        prestate = conn.prepareStatement(sql2);
                        ResultSet result2 = prestate.executeQuery();
                        while (result2.next()) {
                            NurseList.add(GeneralService.getNurse(result2.getInt("n_id")));
                        }
                        break;
                    case 3:
                        String sql3 = "select id from app_nurse where work_age<=9 and work>=7 ";
                        prestate = conn.prepareStatement(sql3);
                        ResultSet result3 = prestate.executeQuery();
                        while (result3.next()) {
                            NurseList.add(GeneralService.getNurse(result3.getInt("n_id")));
                        }
                        break;
                    case 4:
                        String sql4 = "select id from app_nurse where work_age>=10 ";
                        prestate = conn.prepareStatement(sql4);
                        ResultSet result4 = prestate.executeQuery();
                        while (result4.next()) {
                            NurseList.add(GeneralService.getNurse(result4.getInt("n_id")));
                        }
                        break;
                }
                break;
            case 3:
                String sql5 = "select id from app_nurse where sex= ?  ";
                prestate = conn.prepareStatement(sql5);
                prestate.setInt(1, position - 1);    ///看位置是不是正确
                ResultSet result5 = prestate.executeQuery();
                while (result5.next()) {
                    NurseList.add(GeneralService.getNurse(result5.getInt("n_id")));
                }
                break;
            case 4:
                switch (position) {
                    case 1:
                        String sql6 = "select id from app_nurse where evaluation<60 and work>=0 ";
                        prestate = conn.prepareStatement(sql6);
                        ResultSet result6 = prestate.executeQuery();
                        while (result6.next()) {
                            NurseList.add(GeneralService.getNurse(result6.getInt("n_id")));
                        }
                        break;
                    case 2:
                        String sql7 = "select id from app_nurse where evaluation<70 and work>=60 ";
                        prestate = conn.prepareStatement(sql7);
                        ResultSet result7 = prestate.executeQuery();
                        while (result7.next()) {
                            NurseList.add(GeneralService.getNurse(result7.getInt("n_id")));
                        }
                        break;
                    case 3:
                        String sql8 = "select id from app_nurse where evaluation<80 and work>=70 ";
                        prestate = conn.prepareStatement(sql8);
                        ResultSet result8 = prestate.executeQuery();
                        while (result8.next()) {
                            NurseList.add(GeneralService.getNurse(result8.getInt("n_id")));
                        }
                        break;
                    case 4:
                        String sql9 = "select id from app_nurse where evaluation<90 and work>=80 ";
                        prestate = conn.prepareStatement(sql9);
                        ResultSet result9 = prestate.executeQuery();
                        while (result9.next()) {
                            NurseList.add(GeneralService.getNurse(result9.getInt("n_id")));
                        }
                        break;
                    case 5:
                        String sql10 = "select id from app_nurse where evaluation<=100 and work>=90 ";
                        prestate = conn.prepareStatement(sql10);
                        ResultSet result10 = prestate.executeQuery();
                        while (result10.next()) {
                            NurseList.add(GeneralService.getNurse(result10.getInt("n_id")));
                        }
                        break;
                }

        }

        return NurseList;
    }

    static public ArrayList<String> getNurseName() throws SQLException {

        ArrayList<String> nurseNameList = new ArrayList<>();
        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        String sql = "select name from app_nurse";

        prestate = conn.prepareStatement(sql);
        ResultSet result = prestate.executeQuery();
        while (result.next()) {
            nurseNameList.add(result.getString("name"));
        }

        return nurseNameList;

    }

    static public ArrayList<Integer> getNurseArea(int id) throws SQLException {

        ArrayList<Integer> nurseAreaList = new ArrayList<>();

        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        String sql = "select * from app_range where n_id =?";
        prestate = conn.prepareStatement(sql);
        prestate.setInt(1, id);
        ResultSet result = prestate.executeQuery();


        while (result.next()) {

            int area = result.getInt("type");
            nurseAreaList.add(area);

        }

        return nurseAreaList;

    }

    /*管理员增加护工
        *
        * */

    static  public boolean addNurse(Nurse nurse) throws SQLException {
        Connection conn = DBconnect.getConn();


        PreparedStatement prestate;

            String sql = "insert into app_nurse(name,sex,age,work_age,price,evaluation,phone,height,weight,blood_type,nation,identity,constellation,animal,description,area) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql1 = "select max(id) FROM app_nurse";
            String sql2 ="insert into app_range(n_id,type) values(?,?) ";
                    prestate = (PreparedStatement) conn.prepareStatement(sql);


            prestate.setString(1,nurse.getNurseName());
            prestate.setInt(2,nurse.getNurseSex());
            prestate.setInt(3,nurse.getNurseAge());
            prestate.setInt(4,nurse.getNurseWorkAge());
            prestate.setInt(5,nurse.getNursePrice());
            prestate.setInt(6,nurse.getNurseEvaluate());
            prestate.setString(7,nurse.getNursePhone());
            prestate.setInt(8,nurse.getNurseHeigt());
            prestate.setInt(9,nurse.getNurseWeight());
            prestate.setString(10,nurse.getNurseBloodType());
            prestate.setString(11,nurse.getNurseNation());
            prestate.setString(12,nurse.getNurseIdentity());
            prestate.setString(13,nurse.getNurseConstellation());
            prestate.setString(14,nurse.getNurseAnimal());
            prestate.setString(15,nurse.getNurseDescription());
            prestate.setString(16,nurse.getNurseArea());



            int i =prestate.executeUpdate();
            int j =0;
            int id;

            prestate = (PreparedStatement) conn.prepareStatement(sql1);
            ResultSet  result =  prestate.executeQuery();

            id = result.getInt(1);



            prestate = (PreparedStatement) conn.prepareStatement(sql2);

            for(int k=0;k<nurse.getNurseProtectArea().size();k++){
                prestate.setInt(1, id);
                prestate.setInt(2, nurse.getNurseProtectArea().get(k));
                 j =j+prestate.executeUpdate();
            }



            if(i==1&&j==nurse.getNurseProtectArea().size()){
                return true;
            }
            else return  false;


        }

        /*管理员修改护工信息
        *
        * */

    static  public boolean modifyNurse(Nurse nurse) throws SQLException {
        Connection conn = DBconnect.getConn();


        PreparedStatement prestate;

        String sql = "update app_nurse set name=?,sex=?,age=?,work_age=?,price=?,evaluation=?,phone=?,height=?,weight=?,blood_type=?,nation=?,identity=?,constellation=?,animal=?,description=?,area=? where id=?";
        String sql1 ="delete from app_range  where n_id = ?";
        String sql2 ="insert into app_range(n_id,type) values(?,?)";
        prestate = (PreparedStatement) conn.prepareStatement(sql);


        prestate.setString(1,nurse.getNurseName());
        prestate.setInt(2,nurse.getNurseSex());
        prestate.setInt(3,nurse.getNurseAge());
        prestate.setInt(4,nurse.getNurseWorkAge());
        prestate.setInt(5,nurse.getNursePrice());
        prestate.setInt(6,nurse.getNurseEvaluate());
        prestate.setString(7,nurse.getNursePhone());
        prestate.setInt(8,nurse.getNurseHeigt());
        prestate.setInt(9,nurse.getNurseWeight());
        prestate.setString(10,nurse.getNurseBloodType());
        prestate.setString(11,nurse.getNurseNation());
        prestate.setString(12,nurse.getNurseIdentity());
        prestate.setString(13,nurse.getNurseConstellation());
        prestate.setString(14,nurse.getNurseAnimal());
        prestate.setString(15,nurse.getNurseDescription());
        prestate.setString(16,nurse.getNurseArea());
        prestate.setString(17,nurse.getNurseIdentity());



        int i =prestate.executeUpdate();
        int j =0,z=0;

        prestate = (PreparedStatement) conn.prepareStatement(sql1);
        j=prestate.executeUpdate();


        prestate = (PreparedStatement) conn.prepareStatement(sql2);
        for(int k=0;k<nurse.getNurseProtectArea().size();k++){
            prestate.setInt(1, nurse.getNurseId());
            prestate.setInt(2, nurse.getNurseProtectArea().get(k));
            z =z+prestate.executeUpdate();
        }



        if(i==1&&z==nurse.getNurseProtectArea().size()){
            return true;
        }
        else return  false;


    }

    static public ArrayList<Nurse> searchNurse(String name) throws SQLException {

        ArrayList<Nurse> nurseList = new ArrayList<>();
        ArrayList<Integer> nurseAreaList = new ArrayList<>();

        Nurse tempNurse;
        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        String sql = "select * from app_nurse where name =?";
        prestate = conn.prepareStatement(sql);
        prestate.setString(1, name);
        ResultSet result = prestate.executeQuery();

        while (result.next()) {

            int nurseID = result.getInt("id");
            String nurseName = result.getString("name");
            int nurseSex = result.getInt("sex");
            int nurseAge = result.getInt("age");
            int nurseWorkAge = result.getInt("work_age");
            int nursePrice = result.getInt("price");
            int nurseEvaluation = result.getInt("evaluation");
            String nursePhone = result.getString("phone");
            int nurseHeight = result.getInt("height");
            int nurseWeight = result.getInt("weight");
            String nurseBloodType = result.getString("blood_type");
            String nurseNation = result.getString("nation");
            String nurseIdentity = result.getString("identity");
            String nurseConstellation = result.getString("constellation");
            String nurseAnimal = result.getString("animal");
            String nurseDescription = result.getString("description");
            String nurseArea = result.getString("area");
            nurseAreaList = NurseService.getNurseArea(nurseID);

            tempNurse = new Nurse(nurseName, nurseSex, nurseAge, nurseWorkAge,
                    nurseArea, nurseEvaluation, nursePrice, nurseAreaList, nurseHeight,
                    nurseWeight, nurseBloodType, nurseNation, nurseIdentity,
                    nurseConstellation, nurseAnimal, nurseDescription, nursePhone);
            tempNurse.setNurseId(nurseID);

        }


        return nurseList;

    }

    static public ArrayList<Nurse> getNurseList() throws SQLException {

        ArrayList<Nurse> nurseList = new ArrayList<>();
        ArrayList<Integer> nurseAreaList = new ArrayList<>();

        Nurse tempNurse;
        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;

        String sql = "select * from app_nurse";
        prestate = conn.prepareStatement(sql);
        ResultSet result = prestate.executeQuery();

        while (result.next()) {

            int nurseID = result.getInt("id");
            String nurseName = result.getString("name");
            int nurseSex = result.getInt("sex");
            int nurseAge = result.getInt("age");
            int nurseWorkAge = result.getInt("work_age");
            int nursePrice = result.getInt("price");
            int nurseEvaluation = result.getInt("evaluation");
            String nursePhone = result.getString("phone");
            int nurseHeight = result.getInt("height");
            int nurseWeight = result.getInt("weight");
            String nurseBloodType = result.getString("blood_type");
            String nurseNation = result.getString("nation");
            String nurseIdentity = result.getString("identity");
            String nurseConstellation = result.getString("constellation");
            String nurseAnimal = result.getString("animal");
            String nurseDescription = result.getString("description");
            String nurseArea = result.getString("area");
            nurseAreaList = NurseService.getNurseArea(nurseID);

            tempNurse = new Nurse(nurseName, nurseSex, nurseAge, nurseWorkAge,
                    nurseArea, nurseEvaluation, nursePrice, nurseAreaList, nurseHeight,
                    nurseWeight, nurseBloodType, nurseNation, nurseIdentity,
                    nurseConstellation, nurseAnimal, nurseDescription, nursePhone);
            tempNurse.setNurseId(nurseID);

            nurseList.add(tempNurse);

        }


        return nurseList;

    }

    static public boolean deleteNurse(int id) throws SQLException {

        boolean flag = false;

        String sql = "select * from app_nurse where name =?";
        Connection conn = DBconnect.getConn();
        PreparedStatement prestate;
        prestate = conn.prepareStatement(sql);
        prestate.setInt(1, id);
        int result = prestate.executeUpdate();

        if (result == 1) {
            flag = true;
        }

        return flag;

        }

    }
