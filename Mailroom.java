package com.cse.ds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: Vicki Chen
 * CSE12 login: cs12sp19af
 * Date: 5/8/19
 * File: Mailroom.java
 * Source of Help: PA5 write up, Piazza
 *
 * This file contains the Mailroom class. 
 * Acts as postal office distributing the mail and packages to the rightful
 * locations and order
 * */

/**
 * This class coordinates the processing and delivering of Deliverable
 * objects. This takes in the consideration of the zip code and the time they
 * arrived to the mailroom
 * */
public class Mailroom {

    private HashMap<Deliverable,MyQueue<Deliverable>> deliveryBins;
    private int currentTimestamp;

    private static final String TEMP_MSG = "hello";
    private static final int ZIP_LENGTH = 5;
    private static final String DASH_LINE = "-";

    /**
     * Creates a Mailroom object intializing its HashMap
     * @param none
     * @return Mailroom object
     * */
    public Mailroom() {

        deliveryBins = new HashMap<Deliverable,MyQueue<Deliverable>>();
        this.currentTimestamp = 0;

    }

    /**
     * Set the timestamp of Deliverable object and add it to the appropriate
     * MyQueue in hashmap
     * @param d Deliverable object to register
     * @return void
     * */
    public void registerItem(Deliverable d) throws NullPointerException  {
        if (d == null)
        {
            throw new NullPointerException();
        }
        //set a time for object
        d.setTimestamp(currentTimestamp);

        //check if zipcode is already in hashmap
        if (!(deliveryBins.containsKey(d)))
        {
            //create new queue if doesn't exist and put in the map
            MyQueue myQ = new MyQueue<Deliverable>();
            deliveryBins.put(d, myQ);
            deliveryBins.get(d).enqueue(d);
        }
        else
        {
            //add to queue corresponds with an existing key
            deliveryBins.get(d).enqueue(d);
        }
        currentTimestamp++;

    }

    /**
     * Removes and returns the Deliverable object that currently possesses the
     * earliest timestamp
     * @param none
     * @return removedItem removed item
     * */
    public Deliverable deliverEarliest() {
        if (deliveryBins.isEmpty() == true)
        {
            return null;
        }

        //get earliest registered item
        Deliverable earliestItem = this.checkEarliest();

        //remove the earliest item from map
        Deliverable removedItem = deliveryBins.get(earliestItem).dequeue();

        //delete key if no more items corresponds with it
        if (deliveryBins.get(earliestItem).isEmpty() == true)
        {
            deliveryBins.remove(earliestItem);
        }

        return removedItem;

    }

    /**
     * Removes and returns the Deliverable object that currently posesses the
     * earliest timestamp out of all Deliverable objects that have a receiver
     * zipcode identical to zip
     * @param zip Given zip code
     * @return removedItem removed item
     * */
    public Deliverable deliverEarliest(String zip) {
        if (deliveryBins.isEmpty() == true)
        {
            return null;
        }

        Deliverable zipCode = new MyMail(0, zip, zip, TEMP_MSG);

        //check if zipcode is even in bin
        if (!(deliveryBins.containsKey(zipCode)))
        {
            return null;
        }

        //remove the earliest item from the map
        Deliverable removedItem = deliveryBins.get(zipCode).dequeue();

        //delete key if no more items corresponds with it
        if (deliveryBins.get(zipCode).isEmpty() == true)
        {
            deliveryBins.remove(zipCode);
        }

        return removedItem;
    }

    /**
     * Returns the Deliverable object that current possesses the earliest 
     * timestamp
     * @param none
     * @return earliestItem Item with earliest timestamp
     * */
    public Deliverable checkEarliest() {
        if (deliveryBins.isEmpty() == true)
        {
            return null;
        }

        Deliverable earliestItem = null;
        //comparing time tool
        int earliestTime = Integer.MAX_VALUE;

        //iterate through hashmap to get each queue
        for (Deliverable d: deliveryBins.keySet())
        {
            MyQueue<Deliverable> queue = deliveryBins.get(d);

            //check each queue's earliest item (front of queue) and compare to 
            //see which has the earliest time
            if (queue.peek().getTimestamp() < earliestTime)
            {
                earliestTime = queue.peek().getTimestamp();
                earliestItem = queue.peek();
            }
        }

        return earliestItem;
    }

    /**
     * Returns the Deliverable object that current possesses the earliest 
     * timestamp out of all Deliverable objects that have a receiver zip code
     * identical to zip
     * @param zip Given zip code
     * @return earliestItem Item with earliest timestamp
     * */
    public Deliverable checkEarliest(String zip) {

        if (deliveryBins.isEmpty() == true)
        {
            return null;
        }

        Deliverable earliestItem = null;

        //create a deliverable object to use zipcode
        Deliverable zipCode = new MyMail(0, zip, zip, TEMP_MSG);

        //iterate through hashmap to find the queue with the given zipcode
        if (deliveryBins.containsKey(zipCode))
        {
            MyQueue<Deliverable> queue = deliveryBins.get(zipCode);

            //get earliest item from front of queue
            int earliestTime = queue.peek().getTimestamp();
            earliestItem = queue.peek();
        }
        return earliestItem;
    }

    /**
     * Removes and returns all Deliverable objects currently in the mailroom
     * as an ArrayList of type Deliverable.
     * @param none
     * @return delivery ArrayList of all objects in mailroom
     * */
    public ArrayList<Deliverable> deliverAll() {

        ArrayList<Deliverable> delivery = new ArrayList<Deliverable>();

        int totalItems = 0;

        //iterate to find the amount of items in the delivery bin
        for (Deliverable d: deliveryBins.keySet())
        {
            totalItems = totalItems + deliveryBins.get(d).size();
        }

        //iterate to find every next earliest item in entire delivery bin
        for (int i = 0; i < totalItems; i++)
        {
            delivery.add(this.deliverEarliest());
        }

        return delivery;

    }

    /**
     * Removes and returns all Deliverable objects that have the same receiver
     * zipcode as zip in an ArrayList of type Deliverable
     * @param zip Given zip code
     * @return delivery ArrayList of all objects with same zip in mailroom
     * */
    public ArrayList<Deliverable> deliverAll(String zip){
        ArrayList<Deliverable> delivery = new ArrayList<Deliverable>();

        //int totalItems = 0;

        //create a deliverable object to get the zipcode
        Deliverable zipCode = new MyMail(0, zip, zip, TEMP_MSG);

        if(!(this.deliveryBins.containsKey(zipCode))) {
            return delivery;
        }

        //get number of items in the queue corresponding to zipcode
        int totalItems = deliveryBins.get(zipCode).size();

        //iterate to find the next earliest item in the queue
        for (int i = 0; i < totalItems; i++)
        {
            delivery.add(this.deliverEarliest(zip));
        }

        return delivery;

    }

    /**
     * Removes the Deliverable object that currently possesses the earliest
     * timestamp out of all Deliverable objects and compare it to the weight 
     * limit,then adds it into an ArrayList of type Deliverable. 
     * @param capacity Weight limit
     * @return Arraylist of removed items
     * */
    public ArrayList<Deliverable> deliverByWeight(int capacity) {

        ArrayList<Deliverable> delivery = new ArrayList<Deliverable>();

        int totalItems = 0;

        //iterate to find the total amount of items in delivery bin to
        //check its weight
        for (Deliverable d: deliveryBins.keySet())
        {
            totalItems = totalItems + deliveryBins.get(d).size();
        }

        //counter
        int currentWeight = 0;

        //iterate through to check through all weights
        for (int i = 0; i < totalItems; i++)
        {
            Deliverable item = this.checkEarliest();

            //checks if next item will exceed capacity
            //if not add to arraylist
            if (currentWeight + item.getWeight() <= capacity)
            {
                currentWeight = currentWeight + item.getWeight();
                delivery.add(this.deliverEarliest());
            }

        }

        return delivery;
    }

    /**
     * Removes the Deliverable object that currently possesses the earliest
     * timestamp out of all Deliverable objects that have a receiver address
     * zipcode identical to zip and compare it to the weight limit,
     * then adds it into an ArrayList of type Deliverable. 
     * @param capacity Weight limit
     * @param zip Given zip
     * @return Arraylist of removed items
     * */
    public ArrayList<Deliverable> deliverByWeight(int capacity, String zip) {

        ArrayList<Deliverable> delivery = new ArrayList<Deliverable>();

        Deliverable zipCode = new MyMail(0, zip, zip, TEMP_MSG);

        if (!(deliveryBins.containsKey(zipCode)))
        {
            return delivery;
        }
        //create to get zipcode
        int totalItems = deliveryBins.get(zipCode).size();

        int currentWeight = 0;

        //iterate through to check all weights
        for (int i = 0; i < totalItems; i++)
        {
            Deliverable item = this.checkEarliest(zip);

            //checks if next item will exceed capacity
            //if not add to arraylist

            if (currentWeight + item.getWeight() <= capacity)
            {
                currentWeight = currentWeight + item.getWeight();
                delivery.add(this.deliverEarliest(zip));
            }
        }

        return delivery;
    }


    /**
     * Merges all of the queues stored by each key that has a corresponding
     * zip code that starts with the prefix
     * @param prefix Given prefix
     * @return void
     * */
    public void mergeBins(String prefix) {
        if (prefix.length() > ZIP_LENGTH || prefix.length() < 0)
        {
            return;
        }

        ArrayList<Deliverable> keys = new ArrayList<Deliverable>();
        ArrayList<String> keyString = new ArrayList<String>();
        MyQueue<Deliverable> queue = new MyQueue<Deliverable>();

        //iterate to get all the different keys that matches with the 
        //prefix to an arraylist
        for (Deliverable d: deliveryBins.keySet())
        {
            String zipCode = deliveryBins.get(d).peek().getZipCode();

            if (zipCode.startsWith(prefix) == true)
            {
                keyString.add(zipCode);
            }
        }

        if (keyString.isEmpty() == true)
        {   
            return;
        }

        //place the key's entire queue into an arraylist one after another
        for (int i = 0; i < keyString.size(); i++)
        {
            keys.addAll(this.deliverAll(keyString.get(i)));
        }


        //use selection sort to sort keys by ascending time
        for (int i = 0; i < keys.size()-1; i++)
        {
            int index = i;

            for (int j = i+1; j < keys.size(); j++)
            {
                //compare the two timestamps to see which is earlier
                if (keys.get(j).getTimestamp() < 
                        keys.get(index).getTimestamp())
                {
                    index = j;
                }
            }

            //swap founded smallest value to left most unsorted index
            Deliverable smallerNum = keys.get(index);
            keys.set(index, keys.get(i));
            keys.set(i, smallerNum);
        }

        //iterate to put keys back into a queue
        for (int i = 0; i < keys.size(); i++)
        {
            queue.enqueue(keys.get(i));
        }

        String newKey = prefix;

        //iterate to replace empty spots in key with dash lines (zipcodes have
        //to be 5 char length)
        for (int i = 0; i < ZIP_LENGTH-prefix.length(); i++)
        {
            newKey = newKey + DASH_LINE;
        }

        Deliverable zipCode = new MyMail(0, newKey, newKey, TEMP_MSG);

        //put new queue into delivery bin
        deliveryBins.put(zipCode, queue);



    }

}
