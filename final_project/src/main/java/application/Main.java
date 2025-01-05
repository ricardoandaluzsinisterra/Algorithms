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
        displayRequests(requests);
        userWithHighestNumberOfRequests(requests);
    }
    
    public static void displayRequests(LinkedList<Request> requests){
        requests.sort(null);
        for (Request request: requests){
            System.out.println(request.format());
        }
    }

    public static void userWithHighestNumberOfRequests(LinkedList<Request> requests){
        HashMap<String, Request> map = DataUtilities.linkedToMap(requests);
        map.display();
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
}