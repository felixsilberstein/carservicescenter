package com.felixsilberstein.service;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.dao.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceAppointmentImpl implements ServiceAppointment {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Integer create(Appointment newAppointment) {
        return appointmentRepository.create(newAppointment);
    }

    @Override
    public void update(Appointment newInstance) {
        appointmentRepository.update(newInstance);
    }

    @Override
    public void deleteById(Integer id) {
        appointmentRepository.deleteById(id);
    }

}
