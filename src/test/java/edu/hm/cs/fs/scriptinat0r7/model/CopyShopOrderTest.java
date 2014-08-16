package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.junit.Test;

/**
 * Test class for the {@code CopyShopOrder}.
 */
public class CopyShopOrderTest {

    /**
     * Tests the hashCode method.
     */
    @Test
    public void testHashCode() {
        CopyShopOrder firstOrder = new CopyShopOrder();
        CopyShopOrder secondOrder = new CopyShopOrder();

        firstOrder.setId(1);
        secondOrder.setId(1);
        Date date = new Date();
        firstOrder.setOrderDate(date);
        secondOrder.setOrderDate(date);

        assertEquals("The hash codes are not equal.", firstOrder.hashCode(), secondOrder.hashCode());
    }

    /**
     * Tests the getter and setter of the id.
     */
    @Test
    public void testGetAndSetId() {
        CopyShopOrder order = new CopyShopOrder();
        int expected = 3;

        order.setId(expected);
        int actual = order.getId();

        assertSame("The id is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the orderDate.
     */
    @Test
    public void testGetAndSetOrderDate() {
        CopyShopOrder order = new CopyShopOrder();
        Date expected = new Date();

        order.setOrderDate(expected);
        Date actual = order.getOrderDate();

        assertEquals("The order date is not equal.", expected, actual);
        assertNotSame("The order date object is the same but should not.", expected, actual);
    }

    /**
     * Tests the getter and setter of the printoutDelivery.
     */
    @Test
    public void testGetAndSetPrintoutDelivery() {
        CopyShopOrder order = new CopyShopOrder();
        Date expected = new Date();

        order.setPrintoutDelivery(expected);
        Date actual = order.getPrintoutDelivery();

        assertEquals("The printout delivery date is not equal.", expected, actual);
        assertNotSame("The printout delivery date object is the same but should not.", expected, actual);
    }

    /**
     * Tests the getter and setter of the studentOrders.
     */
    @Test
    public void testGetAndSetStudentOrders() {
        CopyShopOrder order = new CopyShopOrder();
        Set<StudentOrder> expected = Collections.emptySet();

        order.setStudentOrders(expected);
        Set<StudentOrder> actual = order.getStudentOrders();

        assertSame("The student orders are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the studentOrders.
     */
    @Test
    public void testAddStudentOrder() {
        CopyShopOrder order = new CopyShopOrder();
        StudentOrder studentOrder = new StudentOrder();

        order.addStudentOrder(studentOrder);

        assertEquals("The set of student orders has not 1 element.", 1, order.getStudentOrders().size());
    }

    /**
     * Tests the remove method of the studentOrders.
     */
    @Test
    public void testRemoveStudentOrder() {
        CopyShopOrder order = new CopyShopOrder();
        StudentOrder studentOrder = new StudentOrder();
        order.addStudentOrder(studentOrder);
        assertEquals("The set of student orders has not 1 element.", 1, order.getStudentOrders().size());

        order.removeStudentOrder(studentOrder);
        assertEquals("The set of student orders has not 0 elements.", 0, order.getStudentOrders().size());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        CopyShopOrder order = new CopyShopOrder();
        order.setId(1);
        Date orderDate = new Date();
        Date printoutDelivery = new Date();
        order.setOrderDate(orderDate);
        order.setPrintoutDelivery(printoutDelivery);
        String expected = "CopyShopOrder [id=" + 1 + ", orderDate=" + orderDate + ", printoutDelivery="
                + printoutDelivery + "]";

        String actual = order.toString();

        assertEquals("The toString does not return expected String.", expected, actual);
    }
}
