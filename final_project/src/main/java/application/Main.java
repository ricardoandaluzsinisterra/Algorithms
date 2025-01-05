package application;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Request;
import utils.DataUtilities;
import utils.HashMap;
import utils.StringArrayList;





public class Main {
    public static void main(String[] args) {
        LinkedList<Request> requests = DataUtilities.fileToArray("src/main/resources/dataset1.txt");
        HashMap<String, Request> userMap = DataUtilities.linkedToMap(requests, "userId");
        HashMap<String, Request> typeMap = DataUtilities.linkedToMap(requests, "requestType");
        displayRequests(requests);
        userWithHighestNumberOfRequests(userMap);
        filterAndDisplay(userMap, "UserB");
        identifyRequestType(typeMap, "GET");

    }
    
    public static void displayRequests(LinkedList<Request> requests){
        requests.sort(null);
        for (Request request: requests){
            System.out.println(request.format());
        }
    }

    public static void userWithHighestNumberOfRequests(HashMap<String, Request> map){
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
                //Since it is the earliest I get the size of the requests array and use that to get the last on the list which would be the earliest.
                if ((currentRequests.get(currentRequests.size()-1).compareTo(previousRequests.get(previousRequests.size()-1))) > 0){
                    maxRequests = types.size();
                    maxRequestsKey = key;
                }
            }
        }
        System.out.println("\nThe user with the most unique requests is:" + maxRequestsKey);
    }

    public static void filterAndDisplay(HashMap<String, Request> map, String userId){
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

}