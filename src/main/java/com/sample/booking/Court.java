package com.sample.booking;

import java.util.HashSet;
import java.util.Set;

public class Court {
    private final String name;
    private final Set<Booking> bookings = new HashSet<>();

    public Court(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean bookSlot(String startTime) {
        return bookings.add(new Booking(startTime));
    }
}
