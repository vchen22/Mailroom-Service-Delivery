package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 5/8/19
 * File: Deliverable.java
 * Source of Help: PA5 write up, Piazza
 *
 * This file contains the class Deliverable.
 * Specifies the characteristics of Deliverable such as mails and packages at
 * a postal office
 **/

/**
 * This abstract class will act as the data type for populating the queue.
 * Has methods to retrieves attributes of the Deliverable objects.
 * */
public abstract class Deliverable {

    protected int id;
    protected int weight;
    protected int timestamp;
    protected String fromAddress;
    protected String toAddress;

    /**
     * Returns the id number of the deliverable
     * @param none
     * @return id ID number
     * */
    public int getId() {
        return id; 
    }

    /**
     * Returns the weight of the deliverable in lbs
     * @param none
     * @return weight Weight of deliverable
     * */
    public int getWeight() { 
        return weight; 
    }

    /**
     * Returns the timestamp indicating when the deliverable was registered
     * in the mailroom.
     * @param none
     * @return timestamp Timestamp of Deliverable
     * */
    public int getTimestamp() { 
        return timestamp; 
    }

    /**
     * Sets the timestamp indicating when the deliverable was registered in
     * the mailroom
     * @param timestamp Registered time
     * @return void
     * */
    public void setTimestamp(int timestamp) { 
        this.timestamp = timestamp; 
    }

    /**
     * Returns the sender's address
     * @param none
     * @return fromAddress Sender's address
     * */
    public String getFromAddress() { return fromAddress; }

    /**
     * Returns the receiver address
     * @param none
     * @return toAddress receiver's address
     * */
    public String getToAddress() {
        return toAddress; 
    }

    /**
     * Returns the zip code of the receiver's address
     * @param none
     * */
    public abstract String getZipCode();

    /**
     * Returns the hashcode determind by the deliverable.
     * @param none
     * @return Hashcode of zipcode
     * */
    @Override
        public int hashCode() {
            //get the hashcode of zipcode
            return this.getZipCode().hashCode();
        }

    /**
     * Returns whether if this and obj are equivalent
     * @param obj Comparing object
     * @return if pbjects are equivalent or not
     * */
    @Override
        public boolean equals(Object obj) {
            if (obj == null)
            {
                return false;
            }

            if (obj instanceof Deliverable)
            {
                //cast object to deliverable to compare
                Deliverable object = (Deliverable) obj;

                //equivalent if zipcode equals
                if (this.getZipCode().equals(object.getZipCode()))
                {
                    return true;
                }
            }

            return false;

        }
}
