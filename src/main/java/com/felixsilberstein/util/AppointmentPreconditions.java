package com.felixsilberstein.util;

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
     * @throws AppointmentUnavailableException
     */
    public static <T> T checkNotFound(final T resource) {
        if (resource != null) {
            throw new AppointmentUnavailableException();
        }
        return resource;
    }
    /**
     * Check if some value was found, otherwise throw exception.
     * Used when adding/creating items
     * @param resource
     *            has value true if found, otherwise false
     * @throws AppointmentUnavailableException
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new AppointmentUnavailableException();
        }
        return resource;
    }

    /**
     * Check if some value is not null, otherwise throws NullPointerException.
     * @param resource
     * @param <T>
     * @return resource
     */
    public static <T> T checkNotNull(final T resource) {
        //
        if (resource == null) {
            throw new NullPointerException();
        }
        return resource;
    }
}
