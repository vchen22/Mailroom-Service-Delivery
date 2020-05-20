package com.cse.ds;

import org.junit.*;
import org.junit.runners.MethodSorters;
import java.util.*;

@FixMethodOrder(MethodSorters.JVM)
public class TestMyQueue {

    static MyQueue<Integer> intQueue = null;

    @Before
    public void testEnqueue() {
        intQueue = new MyQueue();
    }


    @Test
    public void testSize() {
        Assert.assertTrue(intQueue.size() == 0);
    }

    @Test
    public void testEnqueueIsEmpty() {
        intQueue.enqueue(1);
        Assert.assertFalse(intQueue.isEmpty());
    }

    @Test 
    public void testEnqueueMultiple() {
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);

        Assert.assertEquals(intQueue.size(), 3);
        Assert.assertEquals(intQueue.peek(), new Integer(1));
    }
    
    @Test
    public void testDequeue() {
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.dequeue();

        Assert.assertEquals(intQueue.size(), 2);
        Assert.assertEquals(intQueue.peek(), new Integer(2));
   }

   @Test (expected = NoSuchElementException.class)
   public void testDequeueOne() {
       intQueue.enqueue(1);
       intQueue.dequeue();
       intQueue.peek();
   }

    @Test
    public void testDequeueAll() {
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.dequeue();
        intQueue.dequeue();
        intQueue.dequeue();

        Assert.assertTrue(intQueue.isEmpty());
     }        
}
