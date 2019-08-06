package com.felixsilberstein.service;

import java.util.Arrays;
import com.felixsilberstein.config.RandomSchedulerConfig;
import com.felixsilberstein.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Chaos {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{${chaos.service-types}}")
    private Map<Integer,String> serviceTypes;

    @Value("#{T(java.util.Arrays).asList('${chaos.car-models}')}")
    List<String> carModels;

    @Value("#{T(java.util.Arrays).asList('${chaos.top-names}')}")
    List<String> customerNames;

    public Boolean isOn() {
        return on;
    }

    @Value("#{${chaos.switch}}")
    Boolean on;

    public Appointment buildRandomAppointment() {
        Appointment appointment = new Appointment();

        int mechanicId = ThreadLocalRandom.current().nextInt(1, 10);
        appointment.setMechanic(mechanicId);

        Integer serviceTypeId = ThreadLocalRandom.current().nextInt(1, serviceTypes.size() + 1);
        appointment.setServiceType(serviceTypeId);

        Integer namePos = ThreadLocalRandom.current().nextInt(0, customerNames.size());
        appointment.setCustomerName(customerNames.get(namePos));

        Integer carModelsPos = ThreadLocalRandom.current().nextInt(0, carModels.size());
        appointment.setCarId(carModels.get(carModelsPos));

        Calendar appointmentDate =  new GregorianCalendar();
        appointmentDate.setTime(new Date());

        Integer futureHours = ThreadLocalRandom.current().nextInt(1, 24*7);
        appointmentDate.add(Calendar.HOUR, futureHours);
        appointment.setStartDateTime(appointmentDate.getTime());

        appointmentDate.add(Calendar.HOUR, 1);
        appointment.setEndDateTime(appointmentDate.getTime());
        logger.debug(appointment.toString());
        return appointment;
    }
}
