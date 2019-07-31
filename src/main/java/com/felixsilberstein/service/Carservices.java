package com.felixsilberstein.service;

import com.felixsilberstein.dao.AppointmentRepository;
import com.felixsilberstein.model.Appointment;

import java.util.List;

public interface Carservices{
    AppointmentRepository<Appointment, Long> getAppointmentRepository();
    List<Appointment> findAll();

    Appointment findById(Long id);

    Long create(Appointment newAppointment);
}
