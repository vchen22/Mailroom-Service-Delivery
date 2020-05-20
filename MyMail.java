package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 5/8/19
 * File: MyMail.java
 * Source of Help: PA5 write up, Piazza
 *
 * This file contains subclass MyMail.
 * It extends the abstract class Deliverable and can retrieve more information
 * of the mail
 * */

/**
 * This class contains methods that will get the message and zip code of the
 * mail
 * */
public class MyMail extends Deliverable {

    private String message;

    private static final int ZIP_INT = 5;

    /**
     * Intializes a MyMail object
     * @param id ID number
     * @param fromAddress Sender's address
     * @param toAddress Receiever's address
     * @param message Message of mail
     * @return MyMail object
     * */
    public MyMail(int id, String fromAddress, String toAddress, String message) 
    {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.message = message;
        this.weight = 1;
        this.timestamp = -1;
    }

    /**
     * Returns the message stored insid the mail
     * @param none
     * @return message Message of mail
     * */
    public String getMessage() { return this.message; }

    /**
     * Returns the zip code of the receiver address
     * @param none
     * @return zipcode Zip code of receiver's address
     * */
    @Override
        public String getZipCode() {
            String address = this.getToAddress();
            //only get the zipcode of the address which is at the end
            String zipCode = address.substring(address.length()-ZIP_INT, 
                    address.length());
            return zipCode;     
        }

}
