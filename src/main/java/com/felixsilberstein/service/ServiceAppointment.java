package com.felixsilberstein.service;

import com.felixsilberstein.dao.AppointmentRepository;
import com.felixsilberstein.model.Appointment;

import java.util.List;

public interface ServiceAppointment {

    List<Appointment> findAll();

    Appointment findById(Integer id);

    Integer create(Appointment newAppointment);

    void update(Appointment newInstance);

    void deleteById(Integer id);
}
