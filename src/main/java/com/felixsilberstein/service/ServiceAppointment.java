package com.felixsilberstein.service;

import com.felixsilberstein.dao.AppointmentRepository;
import com.felixsilberstein.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface ServiceAppointment {

    List<Appointment> findAll();

    Optional<Appointment> findById(Integer id);

    Integer create(Appointment newAppointment);

    void update(Appointment newInstance);

    void deleteById(Integer id);
}
