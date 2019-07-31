package com.felixsilberstein.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felixsilberstein.util.Format;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    public Appointment(Integer id, String customerName, String carId) {
        this.id = id;
        CustomerName = customerName;
        CarId = carId;
    }
    public Appointment() {

    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", CustomerName='" + CustomerName + '\'' +
                ", CarId='" + CarId + '\'' +
                ", startDateTime=" + Format.Date2MySQLTimestamp(startDateTime) +
                ", endDateTime=" + Format.Date2MySQLTimestamp(endDateTime) +
                ", serviceType=" + serviceType +
                ", mechanic=" + mechanic +
                '}';
    }

    private Integer id;
    private String CustomerName;
    private String CarId;

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
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
