package com.felixsilberstein.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felixsilberstein.util.Format;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Appointment implements Serializable {

    public Appointment() {

    }

    public Appointment(int id, Timestamp start, Timestamp end, int service_type_id, int mechanic_id, String customerName, String carId) {
        this.id = id;
        this.customerName = customerName;
        this.carId = carId;
        this.startDateTime = new Date(start.getTime());
        this.endDateTime = new Date(end.getTime());
        this.mechanic = mechanic_id;
        this.serviceType = service_type_id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", CustomerName='" + customerName + '\'' +
                ", CarId='" + carId + '\'' +
                ", startDateTime=" + Format.Date2MySQLTimestamp(startDateTime) +
                ", endDateTime=" + Format.Date2MySQLTimestamp(endDateTime) +
                ", serviceType=" + serviceType +
                ", mechanic=" + mechanic +
                '}';
    }

    private Integer id;
    private String customerName;
    private String carId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    private Date startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    private Date endDateTime;
    private int serviceType;
    private int mechanic;

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public int getMechanic() {
        return mechanic;
    }

    public void setMechanic(int mechanic) {
        this.mechanic = mechanic;
    }


    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
