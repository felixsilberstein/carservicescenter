package com.felixsilberstein.api;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.service.ServiceAppointment;
import com.felixsilberstein.util.AppointmentPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines API endpoint for appointment management
 * API endpoint base path: /api/
 */
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {

    @Autowired
    private ServiceAppointment service;


    /**
     * Retrieves a list for all Appointments
     * TODO: Add filter arguments
     * TODO: Add paging arguments
     * @return
     */
    @GetMapping
    public List<Appointment> findAll() {
        return service.findAll();
    }

    /**
     * Retrieves an Appointments if id is defined
     * Otherwise returns all Appointments
     * TODO: Add filter arguments
     * TODO: Add paging arguments
     * @return Appointment
     */
    @GetMapping(value = "/{id}")
    public Appointment findAppointment(@PathVariable(name="id", required=true) Integer id) {
        return service.findById(id);
    }

    /**
     * Creates an appointment if there are available mechanics and location at the requested time slot
     * @param newAppointment
     * @return Appointment
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody Appointment newAppointment) {
        AppointmentPreconditions.checkNotFound(service.findById(newAppointment.getId()));
        return service.create(newAppointment);
    }

    /**
     * Updates an appointment
     * @param id
     * @param newInstance
     * @return Updated instance
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Appointment newInstance) {
        AppointmentPreconditions.checkNotNull(newInstance);
        AppointmentPreconditions.checkFound(service.findById(newInstance.getId()));
        service.update(newInstance);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
