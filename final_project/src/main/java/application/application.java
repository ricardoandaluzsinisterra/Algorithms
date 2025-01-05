package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import model.Request;
import utils.DataUtilities;
import utils.HashMap;
import utils.StringArrayList;





public class application {
    public static void main(String[] args) {
        try {
            LinkedList<Request> requests = DataUtilities.fileToArray("src/main/resources/dataset1.txt");
            LinkedList<Request> requests2 = DataUtilities.fileToArray("src/main/resources/dataset2.txt");
            HashMap<String, Request> userMap = DataUtilities.linkedToMap(requests, "userId");
            HashMap<String, Request> typeMap = DataUtilities.linkedToMap(requests, "requestType");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date1 = dateFormat.parse("2024-12-10T08:05:00");
            Date date2 = dateFormat.parse("2024-12-10T08:40:00");
            displayRequests(requests);
            userWithHighestNumberOfRequests(userMap, "earliest");
            filterAndDisplay(userMap, "UserB");
            identifyRequestType(typeMap, "GET");
            identifyRequestsWithinTimeFrame(requests, date1, date2);
            userWithLongestActiveSpan(typeMap);
            LinkedList<Request> deduplicated = deduplication(requests, requests2);
            HashMap<String, Request> deduplicatedMap = DataUtilities.linkedToMap(deduplicated, "userId");
            userWithHighestNumberOfRequests(deduplicatedMap, "recent");
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    public static void displayRequests(LinkedList<Request> requests){
        System.out.println("2. Displays all requests in reverse chronological order, i.e. newest to oldest.\r");
        requests.sort(null);
        for (Request request: requests){
            System.out.println(request.format());
        }
    }

    public static void userWithHighestNumberOfRequests(HashMap<String, Request> map, String flag){
        System.out.println("\n3. Identifies the user with the highest number of unique request types. If two or more users have the same number, display the user with the earliest request in the dataset.");
        ArrayList<String> keys = map.getKeys();
        int maxRequests = 0;
        String maxRequestsKey = null;
        
        for (String key : keys){
            StringArrayList types = new StringArrayList();
            for (Request request : map.get(key)){
                String type = request.getRequestType();
                if(!types.contains(type)){
                    types.add(type);
                }
            }
            if(maxRequests < types.size()){
                maxRequests = types.size();
                maxRequestsKey = key;
            }
            else if(maxRequests == types.size()){
                ArrayList<Request> previousRequests = map.get(maxRequestsKey);
                ArrayList<Request> currentRequests = map.get(key);
                previousRequests.sort(null);
                currentRequests.sort(null);
                switch (flag) {
                    case "earliest":
                        //Since it is the earliest I get the size of the requests array and use that to get the last on the list which would be the earliest.
                        if ((currentRequests.get(currentRequests.size()-1).compareTo(previousRequests.get(previousRequests.size()-1))) > 0){
                            maxRequests = types.size();
                            maxRequestsKey = key;
                        }
                        break;
                    case "recent":
                        if (currentRequests.get(0).compareTo(previousRequests.get(0)) > 0){
                            maxRequests = types.size();
                            maxRequestsKey = key;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }                
            }
        }
        System.out.println("\nThe user with the most unique requests is:" + maxRequestsKey);
    }

    public static void filterAndDisplay(HashMap<String, Request> map, String userId){
        System.out.println("\n4. Filters and displays all requests made by a specific user based on their User ID.");
        ArrayList<Request> requests = map.get(userId);
        if(requests == null){
            System.out.println("No requests found or incorrect user input");
        } 
        else {
            for (Request request : requests) {
                System.out.println(request.format());
            }
        }
    }

    public static void identifyRequestType(HashMap<String, Request> map, String requestType){
        System.out.println("\n5. Identifies all requests of a specified type.");
        ArrayList<Request> requests = map.get(requestType);
        if(requests == null){
            System.out.println("No requests found or incorrect user input");
        } 
        else {
            for (Request request : requests) {
                System.out.println(request.format());
            }
        }
    }

    public static void identifyRequestsWithinTimeFrame(LinkedList<Request> requests, Date after, Date before){
        System.out.println("\n6. Identifies all requests within a specified timestamp range");
        LinkedList<Request> newRequests = new LinkedList<>();
        for (Request request : requests){
            if (request.getTimestamp().compareTo(after) >= 0 && request.getTimestamp().compareTo(before) <= 0){
                newRequests.add(request);
            }
        }
        for (Request request : requests){
            System.out.println(request.format());
        }
    }

    public static void userWithLongestActiveSpan(HashMap<String, Request> map) {
        System.out.println("\nIdentifies the user with the longest active span (i.e. the user with the longest time between their first and last request). If two or more users have the same length span, display the user with the latest request.");
        ArrayList<String> keys = map.getKeys();
        long maxSpan = 0;
        String maxSpanKey = null;
        Date latestRequest = null;
    
        for (String key : keys) {
            ArrayList<Request> requests = map.get(key);
            if (requests == null || requests.isEmpty()) {
                continue;
            }
            requests.sort(null);
            Date firstRequest = requests.get(0).getTimestamp();
            Date lastRequest = requests.get(requests.size() - 1).getTimestamp();
            long span = lastRequest.getTime() - firstRequest.getTime();
    
            if (span > maxSpan) {
                maxSpan = span;
                maxSpanKey = key;
                latestRequest = lastRequest;
            } else if (span == maxSpan) {
                if (lastRequest.compareTo(latestRequest) > 0) {
                    maxSpanKey = key;
                    latestRequest = lastRequest;
                }
            }
        }
        System.out.println("\nThe user with the longest active span is: " + maxSpanKey);
    }

    public static LinkedList<Request> deduplication(LinkedList<Request> original, LinkedList<Request> newRequests){
        System.out.println("\nDeduplicating the new dataset to remove all requests appearing in both the original log and here.");
        original.sort(null);
        newRequests.sort(null);

        LinkedList<Request> deduplicated = new LinkedList<>();
        int counter1 = 0;
        int counter2 = 0;

        while (counter1 < original.size() && counter2 < newRequests.size()){
            Request originalRequest = original.get(counter1);
            Request newRequest = newRequests.get(counter2);

            if (originalRequest.compareTo(newRequest) < 0) {
                counter1++;
            } else if (originalRequest.compareTo(newRequest) > 0) {
                deduplicated.add(newRequest);
                counter2++;
            } else {
                counter1++;
                counter2++;
            }
        }

        while (counter2 < newRequests.size()){
            deduplicated.add(newRequests.get(counter2++));
        }
        return deduplicated;
    }

}