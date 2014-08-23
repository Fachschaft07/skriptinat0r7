package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class ProfessorTest {
    
    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Professor.class).withRedefinedSuperclass().verify();
    }

    @Test
    public void testGetAndSetTitle() throws Exception {
        Professor prof = new Professor();
        String expected = "Prof. Dr.";
        prof.setTitle(expected);
        
        String actual = prof.getTitle();
        
        assertSame("The title of the professor is not the same.", expected, actual);
    }
    @Test
    public void testGetFullName() {
        Professor prof = new Professor();
        String expected = createFullProfessorName(prof);
        
        String actual = prof.getFullName();
        
        assertEquals("The full name of the professor is not equal.", expected, actual);
    }
    
    @Test
    public void testGetFullNameIsNull() throws Exception {
        Professor prof = new Professor();
        prof.setFirstName("Jochen");
        prof.setLastName("Hertle");
        String expected = "Jochen Hertle";
        
        String actual = prof.getFullName();
        
        assertEquals("The full name of the professor is not equal.", expected, actual);
    }
    
    @Test
    public void testGetFullNameIsEmpty() throws Exception {
        Professor prof = new Professor();
        prof.setTitle("");
        prof.setFirstName("Jochen");
        prof.setLastName("Hertle");
        String expected = "Jochen Hertle";
        
        String actual = prof.getFullName();
        
        assertEquals("The full name of the professor is not equal.", expected, actual);
    }

    @Test
    public void testToString() {
        Professor prof = new Professor();
        createFullProfessorName(prof);
        String expected = "Professor [title=" + prof.getTitle() + ", firstName=" + prof.getFirstName() + ", lastName="
                + prof.getLastName() + "]";

        String actual = prof.toString();

        assertEquals("The toString does not return expected String.", expected, actual);
    }

    private String createFullProfessorName(Professor prof) {
        String title = "Prof. Dr.";
        String firstName = "Jochen";
        String lastName = "Hertle";
        prof.setTitle(title);
        prof.setFirstName(firstName);
        prof.setLastName(lastName);
        return title + " " + firstName + " " + lastName;
    }

}
