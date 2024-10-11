package ca2.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ca2.model.Event;
import ca2.model.EventGenerator;

public class UsingEvents {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Create an array of 10 random events and display it:");
        Event [] events = new Event[10];
        for (int i = 0; i < events.length; i++) {
            events[i] = EventGenerator.generateEvent();
        }
        displayEvents(events);

        while (true) { 
            System.out.print("\nMenu:");
            System.out.print("1. Display all Events");
            System.out.print("2. Display all Events from a specific source");
            System.out.print("3. Generate a new Event and insert it into the array");
            System.out.print("4. View active sources");
            System.out.print("5. Clear all events before a specific timestamp. This should display all cleared events");
            System.out.print("6. Exit the program");
            int choice = 0;
            try {
                choice  = scanner.nextInt();
                scanner.nextLine();
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    displayEvents(events);
                    break;
                case 2:
                    displayEventsFromSource(events);
                    break;
                case 3:
                    generateEventAndInsert(events);
                    break;
                case 4:
                    displayActiveSources(events);
                    break;
                case 5:
                    clearEvents(events);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public static void displayEvents(Event[] eventArray) {
        int count = 0;
        for (Event event : eventArray) {
            count += 1;
            System.out.println(event + " In position: " + (count - 1));
        }
    }

    public static void displayEventsFromSource(Event[] eventArray){
        while (true){
            try {
                String source  = scanner.next();
                scanner.nextLine();
                Event.validateSource(source);
                int counter = 0;
                for (Event event : eventArray){
                    if (event.getSource().equals(source)){
                        counter ++;
                    }
                }

                if (counter == 0) {
                    System.out.println("No events found for the source: " + source);
                    continue;
                }

                Event[] events = new Event[counter];
                int index = 0;
                for (Event event : eventArray){
                    if (event.getSource().equals(source)){
                        events[index++] = event;
                    }
                }
                System.out.println("Events from source: " + source);
                displayEvents(events);
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Invalid source, please try again.");
                scanner.nextLine();
            }
        }
    }

    public static void generateEventAndInsert(Event[] eventArray) {
        Event newEvent = EventGenerator.generateEvent();
        System.out.println(newEvent.format());
        Event.insertSorted(eventArray, newEvent);
    }

    public static void displayActiveSources(Event[] eventArray){
        String[] activeSources = Event.getActiveSources(eventArray);
        System.out.println("The active sources are the following:");
        for (String source :activeSources){
            System.out.println("\n" + source);
        }
    }

    public static void clearEvents(Event[] eventArray) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime deleteBeforeTimestamp = null;

        while (deleteBeforeTimestamp == null) {
            System.out.print("Enter the timestamp (yyyy-MM-dd HH:mm:ss): ");
            String input = scanner.nextLine();
            try {
                deleteBeforeTimestamp = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the timestamp in the format yyyy-MM-dd HH:mm:ss.");
            }
        }

        Event[] deletedEvents = Event.deleteAllBefore(eventArray, deleteBeforeTimestamp);
        System.out.println("Deleted events:");
        for (Event event : deletedEvents) {
            System.out.println(event);
        }
    }
}
