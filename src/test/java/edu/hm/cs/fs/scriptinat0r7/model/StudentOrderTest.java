package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

/**
 * Test class for the {@code StudentOrder}.
 */
public class StudentOrderTest {
    
    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(StudentOrder.class).verify();
    }

    /**
     * Tests the getter and setter for the id.
     */
    @Test
    public void testGetAndSetId() {
        StudentOrder order = new StudentOrder();
        int expected = 3;
        
        order.setId(expected);
        int actual = order.getId();
        
        assertEquals("The id of the student order is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter for the copy shop order.
     */
    @Test
    public void testGetAndSetCopyShopOrder() {
        StudentOrder order = new StudentOrder();
        CopyShopOrder expected = new CopyShopOrder();
        
        order.setCopyShopOrder(expected);
        CopyShopOrder actual = order.getCopyShopOrder();
        
        assertSame("The copy shop order to this student order is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter for the script documents.
     */
    @Test
    public void testGetAndSetScriptDocuments() {
        StudentOrder order = new StudentOrder();
        Set<ScriptDocument> expected = Collections.emptySet();
        
        order.setScriptDocuments(expected);
        Set<ScriptDocument> actual = order.getScriptDocuments();
        
        assertSame("The script documents of the student order are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the script documents.
     */
    @Test
    public void testAddScriptDocument() {
        StudentOrder order = new StudentOrder();
        ScriptDocument document = new ScriptDocument();
        
        order.addScriptDocument(document);
        
        assertEquals("The set of script documents has not 1 element.", 1, order.getScriptDocuments().size());
    }
    
    /**
     * Tests the add method of the script documents when there is already an existing set.
     */
    @Test
    public void testAddScriptDocumentExistingSet() {
        StudentOrder order = new StudentOrder();
        ScriptDocument document = new ScriptDocument();
        Set<ScriptDocument> documents = new HashSet<ScriptDocument>();
        order.setScriptDocuments(documents);
        
        order.addScriptDocument(document);
        
        assertEquals("The set of script documents has not 1 element.", 1, order.getScriptDocuments().size());
    }

    /**
     * Tests the remove method of the script documents.
     */
    @Test
    public void testRemoveScriptDocument() {
        StudentOrder order = new StudentOrder();
        ScriptDocument document = new ScriptDocument();
        order.addScriptDocument(document);
        assertEquals("The set of script documents has not 1 element.", 1, order.getScriptDocuments().size());
        
        order.removeScriptDocument(document);
        assertEquals("The set of script documents has not 0 elements", 0, order.getScriptDocuments().size());
    }

    /**
     * Tests the getter and setter of the order date.
     */
    @Test
    public void testGetAndSetOrderDate() {
        StudentOrder order = new StudentOrder();
        Date expected = new Date();
        
        order.setOrderDate(expected);
        Date actual = order.getOrderDate();
        
        assertEquals("The order date is not equal.", expected, actual);
        assertNotSame("The order date object is the same but should not.", expected, actual);
    }
    
    /**
     * Tests the getter and setter of the order date when it is null.
     */
    @Test
    public void testGetAndSetNullOrderDate() {
        StudentOrder order = new StudentOrder();
        
        order.setOrderDate(null);
        Date actual = order.getOrderDate();
        
        assertNull("The order date is not null.", actual);
    }

    /**
     * Tests the getter and setter of the student pickup date.
     */
    @Test
    public void testGetAndSetStudentPickup() {
        StudentOrder order = new StudentOrder();
        Date expected = new Date();
        
        order.setStudentPickup(expected);
        Date actual = order.getStudentPickup();
        
        assertEquals("The student pickup date is not equal.", expected, actual);
        assertNotSame("The student pickup date object is the same but should not.", expected, actual);        
    }
    
    /**
     * Tests the getter and setter of the student pickup date when it is null.
     */
    @Test
    public void testGetAndSetNullStudentPickup() {
        StudentOrder order = new StudentOrder();
        
        order.setStudentPickup(null);
        Date actual = order.getStudentPickup();
        
        assertNull("The student pickup date is not null.", actual);       
    }

    /**
     * Tests the getter and setter of the notes.
     */
    @Test
    public void testGetAndSetNotes() {
        StudentOrder order = new StudentOrder();
        String expected = "Some notes";
        
        order.setNotes(expected);
        String actual = order.getNotes();
        
        assertEquals("The notes of the student order are not equal.", expected, actual);
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        StudentOrder order = new StudentOrder();
        Date date = new Date();
        order.setId(3);
        order.setOrderDate(date);
        order.setStudentPickup(date);
        
        String expected = "StudentOrder [id=3, orderDate=" + date + ", studentPickup=" + date + "]";
        String actual = order.toString();
        
        assertEquals("The toString does not return expected String.", expected, actual);
    }

}
