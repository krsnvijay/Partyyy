package com.notadeveloper.app.pad;

import java.util.ArrayList;

/**
 * Created by Chirag on 18-Jul-17.
 */

public class MySheesha {

  String orderid;
  String orderdate;
  String amount;
  String status;
  ArrayList<String> flavours;
  String deliverby;
  String add1;
  String add2;
  String addpin;
  String phone;
  String name;
  String noofpots;

  public MySheesha() {

  }

  public MySheesha(String orderid, String orderdate, String amount, String status,
      ArrayList<String> flavours, String deliverby, String add1, String add2, String addpin,
      String phone, String name, String noofpots) {
    this.orderid = orderid;
    this.orderdate = orderdate;
    this.amount = amount;
    this.status = status;
    this.flavours = flavours;
    this.deliverby = deliverby;
    this.add1 = add1;
    this.add2 = add2;
    this.addpin = addpin;
    this.phone = phone;
    this.name = name;
    this.noofpots = noofpots;
  }

  public String getNoofpots() {
    return noofpots;
  }

  public void setNoofpots(String noofpots) {
    this.noofpots = noofpots;
  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public String getOrderdate() {
    return orderdate;
  }

  public void setOrderdate(String orderdate) {
    this.orderdate = orderdate;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ArrayList<String> getFlavours() {
    return flavours;
  }

  public void setFlavours(ArrayList<String> flavours) {
    this.flavours = flavours;
  }

  public String getDeliverby() {
    return deliverby;
  }

  public void setDeliverby(String deliverby) {
    this.deliverby = deliverby;
  }

  public String getAdd1() {
    return add1;
  }

  public void setAdd1(String add1) {
    this.add1 = add1;
  }

  public String getAdd2() {
    return add2;
  }

  public void setAdd2(String add2) {
    this.add2 = add2;
  }

  public String getAddpin() {
    return addpin;
  }

  public void setAddpin(String addpin) {
    this.addpin = addpin;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
