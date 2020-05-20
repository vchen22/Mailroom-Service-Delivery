package com.cse.ds;

import java.util.*;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 5/8/19
 * File: MyQueue.java
 * Source of Help: PA5 writeup, Piazza
 * 
 * This file contains the class MyQueue<E>.
 * It implements the data structure queue
 * */

/**
 * This class contains methods that will modifiy the queue at will only occur
 * at either the beginning or the end of the queue
 * @param E
 * */
public class MyQueue<E> {

    private MyQueueNode<E> head;
    private MyQueueNode<E> tail;
    private int nElements;

    /**
     * Initializes a MyQueue object that implements a queue of elements of
     * generic type E
     * @param none
     * @return MyQueue Object
     * */
    public MyQueue() {
        head = new MyQueueNode(null);
        tail = new MyQueueNode(null);
        head.setNext(tail);
        nElements = 0;
    }

    /**
     * Adds an element to the end of the queue
     * @param element Element to add
     * @return void
     * */
    public void enqueue(E element) throws NullPointerException {
        if (element == null)
        {
            throw new NullPointerException();
        }

        //added element is new tail because added at the end
        MyQueueNode newTail = new MyQueueNode(element);

        //with one element, head and tail point the same thing
        if (this.isEmpty() == true)
        {
            tail = newTail;
            head = newTail;
            nElements++;
        }
        else
        {
            //link added element with previous end
            tail.setNext(newTail);
            //make tail the new added element now
            tail = newTail;     
            nElements++;
        }

    }

    /**
     * Removes and returns the element from the head of the queue
     * @param none
     * @return removed Element removed
     * */
    public E dequeue() throws NoSuchElementException {
        if (this.isEmpty() == true)
        {
            throw new NoSuchElementException();
        }

        E removed = head.getElement();

        if (this.size() == 1)
        {
            //create new nodes to restart
            tail = new MyQueueNode(null);
            head = new MyQueueNode(null);
            //relink head and tail
            head.setNext(tail);
            nElements--;
            return removed;
        }

        //head becomes the element after
        head = head.getNext();

        nElements--;
        return removed;

    }

    /**
     * Returns the element at the head of the queue
     * @param none
     * @return Element at the head of queue
     * */
    public E peek() throws NoSuchElementException {
        if (this.isEmpty() == true)
        {
            throw new NoSuchElementException();
        }

        return head.getElement();

    }

    /**
     * Checks if queue is empty or not
     * @param none
     * @return Whether queue is empty or not
     * */
    public boolean isEmpty() {
        if (this.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Returns the number of elements in the queue
     * @param none
     * @return nElements Number of elements in queue
     * */
    public int size() {
        return nElements;
    }

}
