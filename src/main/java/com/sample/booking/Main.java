package com.sample.booking;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Court> courts = setupCourts();
        while(true) {
            System.out.println("Select the court");
            int i = 1;
            for (var court : courts) {
                System.out.println(i + ". " + court.getName());
                i++;
            }

            System.out.println("Press -1 to end");

            Scanner scanner = new Scanner(System.in);
            int inputCourt = scanner.nextInt();
            if(inputCourt == -1)
                break;
            Court court = courts.stream()
                    .filter(c -> c.getName().equals("court" + inputCourt))
                    .collect(Collectors.toList()).get(0);

            System.out.println("Enter slot start time");
            String startTime = new Scanner(System.in).nextLine();

            boolean isSlotBooked = court.bookSlot(startTime);
            if (!isSlotBooked) {
                System.out.println("Slot not available");
            } else {
                System.out.println("Slot booked successfully");
            }
        }
    }

    private static List<Court> setupCourts() {
        return List.of(new Court("court1"), new Court("court2"), new Court("court3"));
    }
}
