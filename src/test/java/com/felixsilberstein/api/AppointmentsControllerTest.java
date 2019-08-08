package com.felixsilberstein.api;


import com.felixsilberstein.dao.AppointmentRepository;
import com.felixsilberstein.model.Appointment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class AppointmentsControllerTest {

    @Mock
    private AppointmentRepository mockRepository;

//    private Appointment appointment;
    private Integer one;

    @BeforeAll
    public void setUp() {
        /*appointment = new Appointment(
                5,
                Timestamp.valueOf("2019-08-31 10:00:00"),
                Timestamp.valueOf("2019-08-31 11:00:00"),
                5,
                1,
                "Test1User",
                "Model T"
        );
        one = new Integer(1);
        System.out.println("------------------------");
        */

    }

    /**
     * Given a known appointment retrive by its id from DB and assert User names are the same
     */
    @Test
//    @DisplayName("Test appointment retrieved from the data base")
    public void getAppointmentById() throws Exception {
        AppointmentRepository mr = mock(AppointmentRepository.class);
        Appointment appointment = new Appointment(
                5,
                Timestamp.valueOf("2019-08-31 10:00:00"),
                Timestamp.valueOf("2019-08-31 11:00:00"),
                5,
                1,
                "Test1User",
                "Model T"
        );

        Mockito.when(mr.findById(Mockito.anyInt())).thenReturn(appointment);

        Appointment dbAppointment = mr.findById(appointment.getId());
        assertEquals("Test1User", dbAppointment.getCustomerName());
        assertEquals("Model T", dbAppointment.getCarId());
    }
}
