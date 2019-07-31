package com.felixsilberstein;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.service.Carservices;
import com.felixsilberstein.util.AppointmentPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Defines API endpoint for appointment management
 * API endpoint base path: /api/
 */
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {

    @Autowired
    private Carservices service;

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
    public Appointment findAppointment(@PathVariable(name="id", required=true) Long id) {
        return service.findById(id);
    }

    /**
     * Creates an appointment if there are available mechanics and location at the requested time slot
     * @param newAppointment
     * @return Appointment
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Appointment newAppointment) {
        AppointmentPreconditions.checkNotFound(service.findById(newAppointment.getId()));
        newAppointment.setStatus(0);
        return service.create(newAppointment);
    }
    /*
    @PutMapping(value = "/{id}")
    public Appointment update(@PathVariable("id") Long id, @RequestBody Appointment newAppointment) {
        Preconditions.checkNotNull(newAppointment);
        Appointment oldAppointment = service.findById(newAppointment.getId());
        if (oldAppointment == null) {
            throw new ResourceNotFoundException();
        }
        return service.update(newAppointment);
    }
    */
}
