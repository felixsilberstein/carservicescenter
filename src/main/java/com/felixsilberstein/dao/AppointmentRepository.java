package com.felixsilberstein.dao;

import com.felixsilberstein.model.Appointment;

import java.util.List;

public interface AppointmentRepository<Appointment, Long> {
    List<Appointment> findAll();

    com.felixsilberstein.model.Appointment findById(java.lang.Long id);

    java.lang.Long create(com.felixsilberstein.model.Appointment newAppointment);
}
