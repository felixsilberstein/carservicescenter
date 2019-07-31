package com.felixsilberstein.util;

import com.felixsilberstein.exception.AppointmentUnavailaleException;

/**
 * Methods to be used before the main processing takes place.
 * If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public class AppointmentPreconditions {
    private AppointmentPreconditions() {
        throw new AssertionError();
    }

    /**
     * Check if some value was NOT found, otherwise throw exception.
     * Used when adding/creating items
     * @param resource
     *            has value true if found, otherwise false
     * @throws AppointmentUnavailaleException
     */
    public static <T> T checkNotFound(final T resource) {
        if (resource != null) {
            throw new AppointmentUnavailaleException();
        }
        return resource;
    }
}
