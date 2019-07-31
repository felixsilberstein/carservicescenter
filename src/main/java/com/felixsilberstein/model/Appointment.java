package com.felixsilberstein.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felixsilberstein.util.Format;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Serializable {
    public Appointment(Long id, String customerName, String carId) {
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
                ", status=" + status +
                ", CustomerName='" + CustomerName + '\'' +
                ", CarId='" + CarId + '\'' +
                ", startDateTime=" + Format.MySQLDate(startDateTime) +
                ", endDateTime=" + Format.MySQLDate(endDateTime) +
                ", serviceType=" + serviceType +
                ", mechanic=" + mechanic +
                '}';
    }

    private Long id;
    private int status;
    private String CustomerName;
    private String CarId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDateTime;
    private int serviceType;
    private int mechanic;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
