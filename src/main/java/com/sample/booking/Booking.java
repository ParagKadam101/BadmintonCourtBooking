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
        LocalTime existingStartTime = LocalTime.parse(startTime);
        LocalTime existingEndTime = existingStartTime.plus(30, ChronoUnit.MINUTES);

        LocalTime inputStartTime = LocalTime.parse(this.startTime);
        LocalTime inputEndTime = inputStartTime.plus(30, ChronoUnit.MINUTES);

        return isInputSlotFallingInBetweenExistingSlot(existingStartTime, existingEndTime, inputStartTime, inputEndTime);
    }

    private boolean isInputSlotFallingInBetweenExistingSlot(LocalTime existingStartTime, LocalTime existingEndTime, LocalTime inputStartTime, LocalTime inputEndTime) {
        return inputStartTime.isAfter(existingStartTime) && inputStartTime.isBefore(existingEndTime) ||
               inputEndTime.isAfter(existingStartTime) && inputEndTime.isBefore(existingEndTime);
    }
}
