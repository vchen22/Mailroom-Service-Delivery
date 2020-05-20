package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 5/8/19
 * File: MyPackage.java
 * Source of Help: PA5 write up, Piazza
 *
 * This file contains the MyPackage subclass.
 * It extends the Deliverable abstract class and can retrieve more information
 * on the package
 * */

/**
 * This class contains methods that will get the contents and zip code of the
 * package
 * @param E
 * */
public class MyPackage<E> extends Deliverable {

    private E content;

    private static final String ZIP_END = ",";

    /**
     * Initializes a MyPackage<E> object that holds an object of generic type E
     * @param id ID number
     * @param fromAddress Sender's address
     * @param toAddress Receiver's address
     * @param content Insides of package
     * @param weight Weight of package
     * @return MyPackage object
     * */
    public MyPackage(int id, String fromAddress, String toAddress,
            E content, int weight)
    {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.content = content;
        this.weight = weight;
        this.timestamp = -1;
    }

    /**
     * Returns the content of the package
     * @param none
     * @return content Insides of package
     * */
    public E getContent() { return this.content; }

    /**
     * Returns the zip code of the receiver's address
     * @param none
     * @return zipcode Receiver's zip code
     * */
    @Override
        public String getZipCode() {

            String address = this.getToAddress();
            //get zipcode in address which is in the front
            String zipCode = address.substring(0, address.indexOf(ZIP_END));
            return zipCode;  

        }

}
