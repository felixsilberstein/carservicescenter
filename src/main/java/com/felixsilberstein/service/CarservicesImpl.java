package com.felixsilberstein.service;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarservicesImpl implements Carservices {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Long create(Appointment newAppointment) {
        return appointmentRepository.create(newAppointment);
    }


}
