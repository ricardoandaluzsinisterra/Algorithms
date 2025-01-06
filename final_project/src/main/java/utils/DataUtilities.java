package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import model.Request;

public class DataUtilities {

    /**
     * Reads a file and converts its contents into a LinkedList of Request objects.
     *
     * @param filename the name of the file to read
     * @return a LinkedList of Request objects
     * @throws IllegalArgumentException if the file cannot be read or the data is invalid
     */
    public static LinkedList<Request> fileToArray(String filename) {
        LinkedList<Request> requests = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            File file = new File(filename);
            System.out.println("Attempting to read file: " + file.getAbsolutePath());
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String rawData = reader.nextLine();
                String[] data = rawData.split(", ");
                if (data.length == 4) {
                    String requestId = data[0];
                    String userId = data[1];
                    Date timestamp = dateFormat.parse(data[2]);
                    String requestType = data[3];
                    Request request = new Request(requestId, userId, timestamp, requestType);
                    requests.add(request);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return requests;
    }

    /**
     * Converts a LinkedList of Request objects into a HashMap based on a specified key.
     *
     * @param requests the LinkedList of Request objects to convert
     * @param key      the key to use for the HashMap (either "userId" or "requestType")
     * @return a HashMap with the specified key and Request objects as values
     * @throws IllegalArgumentException if the key is not "userId" or "requestType"
     */
    public static HashMap<String, Request> linkedToMap(LinkedList<Request> requests, String key) {
        HashMap<String, Request> map = new HashMap<>();
        switch (key) {
            case "userId":
                for (Request request : requests) {
                    map.put(request.getUserID(), request);
                }
                return map;
            case "requestType":
                for (Request request : requests) {
                    map.put(request.getRequestType(), request);
                }
                return map;
            default:
                throw new IllegalArgumentException("No matching key type");
        }
    }
}