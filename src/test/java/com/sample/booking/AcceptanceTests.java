package com.sample.booking;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTests {
    private List<Court> courts;
    private boolean isBooked;

    @Test
    void shouldBeAbleToBookBadmintonSlot() {
        given().courts()
                .whenI().bookCourtForSlot("court1", "07:30")
                .thenI().verifySlotIsBooked();
    }

    @Test
    void shouldNotBeAbleToBookSameBadmintonSlot() {
        given().courts()
                .and().bookCourtForSlot("court1", "07:30");

        given().courts()
                .whenI().bookCourtForSlot("court1", "07:30")
                .thenI().verifySlotNotBooked();
    }

    @Test
    void shouldNotBeAbleToBookConflictingBadmintonSlot() {
        given().courts()
                .and().bookCourtForSlot("court1", "07:30");

        given().courts()
                .whenI().bookCourtForSlot("court1", "07:44")
                .thenI().verifySlotNotBooked();
    }

    private void verifySlotNotBooked() {
        assertFalse(isBooked);
    }

    private AcceptanceTests and() {
        return this;
    }

    private void verifySlotIsBooked() {
        assertTrue(isBooked);
    }

    private AcceptanceTests thenI() {
        return this;
    }

    private AcceptanceTests bookCourtForSlot(String courtId, String startTime) {
        Court court = courts.stream().filter(c -> c.getName().equals(courtId)).collect(Collectors.toList()).get(0);
        isBooked = court.bookSlot(startTime);
        return this;
    }

    private AcceptanceTests whenI() {
        return this;
    }

    private AcceptanceTests courts() {
        if(courts == null) {
            courts = List.of(new Court("court1"), new Court("court2"), new Court("court3"));
        }
        return this;
    }

    private AcceptanceTests given() {
        return this;
    }
}