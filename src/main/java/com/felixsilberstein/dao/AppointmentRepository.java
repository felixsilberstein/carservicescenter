package com.felixsilberstein.dao;

import com.felixsilberstein.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> findAll();

    Appointment findById(Integer id);

    Integer create(Appointment newAppointment);

    void update(Appointment newInstance);

    void deleteById(Integer id);
}
