package com.felixsilberstein.api;


import com.felixsilberstein.dao.AppointmentRepository;
import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.service.ServiceAppointment;
import com.felixsilberstein.service.ServiceAppointmentImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentsControllerTest {

    @Mock
    private AppointmentRepository mockRepository;

    @InjectMocks
    private ServiceAppointmentImpl mockService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAppointmentByIdTest() throws Exception {
        Appointment appointment = new Appointment(
                5,
                Timestamp.valueOf("2019-08-31 10:00:00"),
                Timestamp.valueOf("2019-08-31 11:00:00"),
                5,
                1,
                "Test1User",
                "Model T"
        );

        when(mockRepository.findById(Mockito.anyInt())).thenReturn(appointment);

        Appointment dbAppointment = mockService.findById(1);
        assertEquals("Test1User", dbAppointment.getCustomerName());
        assertEquals("Model T", dbAppointment.getCarId());
    }

    @Test(expected = AppointmentNotFoundException.class)
    public void getNoneExistingAppointmentTest() {
        when(mockRepository.findById(Mockito.anyInt())).thenThrow(AppointmentNotFoundException.class);
        mockService.findById(1);
    }
}
