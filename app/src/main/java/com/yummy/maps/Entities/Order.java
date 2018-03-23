package com.yummy.maps.Entities;

/**
 * Created by Man on 20.03.2018.
 */

public class Order {

    private long id;
    private String addressFrom;
    private String addressTo;
    private String telNumber1;
    private String telNumber2;
    private String dateFrom;
    private String dateTo;
    private String toTime1;
    private String toTime2;
    private String fromTime1;
    private String fromTime2;
    private String comment1;
    private String comment2;
    private String deliverItem;
    private String costOrder;

    public Order(long id, String addressFrom, String addressTo, String telNumber1, String telNumber2, String dateFrom, String dateTo, String toTime1, String toTime2, String fromTime1, String fromTime2, String comment1, String comment2, String deliverItem, String costOrder) {
        this.id = id;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.telNumber1 = telNumber1;
        this.telNumber2 = telNumber2;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.toTime1 = toTime1;
        this.toTime2 = toTime2;
        this.fromTime1 = fromTime1;
        this.fromTime2 = fromTime2;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.deliverItem = deliverItem;
        this.costOrder = costOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getTelNumber1() {
        return telNumber1;
    }

    public void setTelNumber1(String telNumber1) {
        this.telNumber1 = telNumber1;
    }

    public String getTelNumber2() {
        return telNumber2;
    }

    public void setTelNumber2(String telNumber2) {
        this.telNumber2 = telNumber2;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getToTime1() {
        return toTime1;
    }

    public void setToTime1(String toTime1) {
        this.toTime1 = toTime1;
    }

    public String getToTime2() {
        return toTime2;
    }

    public void setToTime2(String toTime2) {
        this.toTime2 = toTime2;
    }

    public String getFromTime1() {
        return fromTime1;
    }

    public void setFromTime1(String fromTime1) {
        this.fromTime1 = fromTime1;
    }

    public String getFromTime2() {
        return fromTime2;
    }

    public void setFromTime2(String fromTime2) {
        this.fromTime2 = fromTime2;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getDeliverItem() {
        return deliverItem;
    }

    public void setDeliverItem(String deliverItem) {
        this.deliverItem = deliverItem;
    }

    public String getCostOrder() {
        return costOrder;
    }

    public void setCostOrder(String costOrder) {
        this.costOrder = costOrder;
    }


}
