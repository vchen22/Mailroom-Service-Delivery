package com.cse.ds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.ArrayList;

//17 test case
@FixMethodOrder(MethodSorters.JVM)
public class TestMailroom {

    static Mailroom mailroom;


    @Before
    public void populate(){
        mailroom = new Mailroom();
    }


    @Test
    public void testRegister() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        mailroom.registerItem(mail);
        Assert.assertTrue(mail == mailroom.checkEarliest());
    }

    @Test
    public void testCheckEarliestNullZip() {
        Assert.assertNull(mailroom.checkEarliest());
    }

    @Test
    public void testCheckEarliestNull() {
        Assert.assertNull(mailroom.checkEarliest("94501"));
    }

    @Test
    public void testDeliverEarliestNullZip() {
        Assert.assertNull(mailroom.deliverEarliest());
    }

    @Test
    public void testDeliverEarliestNull() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        mailroom.registerItem(mail);

        Assert.assertNull(mailroom.checkEarliest("94501"));
    }

    @Test
    public void testDeliverAllEmpty() {
        Assert.assertTrue(mailroom.deliverAll().isEmpty());
    }

    @Test
    public void testDeliverAllZipEmpty() {
        Assert.assertTrue(mailroom.deliverAll("94501").isEmpty());
    }

    @Test
    public void testDeliverByWeightEmpty() {
        Assert.assertTrue(mailroom.deliverByWeight(1).isEmpty());
    }

    @Test
    public void testDeliverByWeightZipEmpty() {
        Assert.assertTrue(mailroom.deliverByWeight(1,"94501").isEmpty());
    }

    @Test
    public void testDeliverMultiple() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertTrue(mail == mailroom.checkEarliest());
        Assert.assertTrue(mail == mailroom.deliverEarliest());
        Assert.assertTrue(mail2 == mailroom.checkEarliest());
        Assert.assertTrue(mail2 == mailroom.deliverEarliest());
        Assert.assertTrue(mail3 == mailroom.checkEarliest());
        Assert.assertTrue(mail3 == mailroom.deliverEarliest());
    }

    @Test
    public void testDeliverMultipleZip() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                   "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertTrue(mail == mailroom.checkEarliest("92632"));
        Assert.assertTrue(mail == mailroom.deliverEarliest("92632"));
        Assert.assertTrue(mail2 == mailroom.checkEarliest("92632"));
        Assert.assertTrue(mail2 == mailroom.deliverEarliest("92632"));
        Assert.assertTrue(mail3 == mailroom.checkEarliest("92632"));
        Assert.assertTrue(mail3 == mailroom.deliverEarliest("92632"));
    }
    @Test
    public void testDeliverMultipleZipDiff() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                   "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertTrue(mail == mailroom.checkEarliest("92632"));
        Assert.assertTrue(mail == mailroom.deliverEarliest("92632"));
        Assert.assertTrue(mail2 == mailroom.checkEarliest("92633"));
        Assert.assertTrue(mail2 == mailroom.deliverEarliest("92633"));
        Assert.assertTrue(mail3 == mailroom.checkEarliest("92632"));
        Assert.assertTrue(mail3 == mailroom.deliverEarliest("92632"));
    }

    @Test
    public void testDeliverAll() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);
        test.add(mail3);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverAll());
    }

    @Test
    public void testDeliverWeightAll() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);
        test.add(mail3);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverByWeight(3));
    }

    @Test
    public void testDeliverWeightLess() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverByWeight(2));
    }

    @Test
    public void testDeliverAllZip() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);
        test.add(mail3);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverAll("92632"));
    }

    @Test
    public void testDeliverWeightZipAll() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);
        test.add(mail3);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverByWeight(3,"92632"));
    }

    @Test
    public void testDeliverWeightZipLess() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        ArrayList<Deliverable> test = new ArrayList<Deliverable>();
        test.add(mail);
        test.add(mail2);

        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        Assert.assertEquals(test, mailroom.deliverByWeight(2,"92632"));
    }


    @Test(expected = NullPointerException.class)
    public void testRegisterNull() {
        mailroom.registerItem(null);
    }

    @Test 
    public void testMerge() { 
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail2 = new MyMail(2,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        Deliverable mail3 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        Deliverable mail4 = new MyMail(3,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92633",
                                    "Hello World!");
        mailroom.registerItem(mail);
        mailroom.registerItem(mail2);
        mailroom.registerItem(mail3);
        mailroom.registerItem(mail4);

        mailroom.mergeBins("926");
        
        Assert.assertTrue(mail == mailroom.checkEarliest("926--"));
        Assert.assertTrue(mail == mailroom.deliverEarliest("926--"));
        Assert.assertTrue(mail2 == mailroom.checkEarliest("926--"));
        Assert.assertTrue(mail2 == mailroom.deliverEarliest("926--"));
        Assert.assertTrue(mail3 == mailroom.checkEarliest("926--"));
        Assert.assertTrue(mail3 == mailroom.deliverEarliest("926--"));
    }

    @Test 
    public void testMergeEmpty() {
        mailroom.mergeBins("926");
        Assert.assertNull(mailroom.checkEarliest());
    }
}
