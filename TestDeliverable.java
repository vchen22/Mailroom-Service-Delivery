package com.cse.ds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.*;

@FixMethodOrder(MethodSorters.JVM)
public class TestDeliverable {

    static Deliverable mail;
    static Deliverable mailTest;
    static Deliverable mailTest2;
    static Deliverable pkg;

    @Before
    public void createDeliverables() {

        String message = "Hello World!";

        String mailFrom = "3811 Nobel Drive,\n La Jolla, CA, USA, 92037";
        String mailTo = "2231 Lebon Drive,\n La Jolla, CA, USA, 92632";
        String mailToTest = "2231 Lebon Drive,\n La Jolla, CA, USA, 92631";
        
        String packageFrom = "92037,\n 3811 Nobel Drive,\n La Jolla, CA, USA";
        String packageTo = "92632,\n 2231 Lebon Drive,\n La Jolla, CA, USA";

        mail = new MyMail(1,mailFrom,mailTo,message);

        mailTest = new MyMail(2,mailFrom,mailTo,message);
        mailTest2 = new MyMail(2,mailFrom,mailToTest,message);

        pkg = new MyPackage<String>(2,packageFrom,packageTo,message,5);

    }

    @Test
    public void testMailZip() {
        Assert.assertTrue(mail.getZipCode().equals("92632"));
    }

    @Test
    public void testPackageZip() {
        Assert.assertTrue(pkg.getZipCode().equals("92632"));
    }

    @Test
    public void testEqualsTrue() {
        Assert.assertEquals(mail.hashCode(), mailTest.hashCode());
    }

    @Test
    public void testEqualsFalse() {
        Assert.assertNotEquals(mail.hashCode(), mailTest2.hashCode());
    }
    
}
