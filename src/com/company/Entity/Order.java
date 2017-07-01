package com.company.Entity;

/**
 * Created by derrickJ on 2017/6/29.
 */
public class Order {

    private int id;
    private int totalPrice;
    private String createTime;
    private String serviceTime;
    private int type; //护理类型: 1.内科 2.外科 3.妇产科
    private int situation; //订单状态: 0.未付款 1.已付款 2.已取消 3.已完成 4.进行中 5.已提醒付款
    private int choseNurse = 1;

    private Nurse nurse = null;
    private Patient patient = null;
    private User user = null;

    public Order(){

    }

    public Order(int id, int totalPrice, String createTime, String serviceTime, int type, int situation, int choseNurse, Nurse nurse, Patient patient, User user) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.serviceTime = serviceTime;
        this.type = type;
        this.situation = situation;
        this.choseNurse = choseNurse;
        this.setNurse(nurse);
        this.setPatient(patient);
        this.setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public int getChoseNurse() {
        return choseNurse;
    }

    public void setChoseNurse(int choseNurse) {
        this.choseNurse = choseNurse;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
