package com.sample.booking;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Booking {
    private final String startTime;

    public Booking(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(startTime, booking.startTime) || isConflictingSlot(booking.startTime);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    private boolean isConflictingSlot(String startTime) {
        LocalTime inputTime = LocalTime.parse(this.startTime);
        LocalTime endTime = LocalTime.parse(startTime).plus(30, ChronoUnit.MINUTES);
        return inputTime.isAfter(LocalTime.parse(startTime)) && inputTime.isBefore(endTime);
    }
}
