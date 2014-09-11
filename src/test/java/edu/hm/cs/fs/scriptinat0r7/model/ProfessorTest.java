package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

/**
 * Test class for the {@code Professor}.
 */
public class ProfessorTest {

    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Professor.class).withRedefinedSuperclass().verify();
    }

    /**
     * Tests the getter and setter for the title.
     */
    @Test
    public void testGetAndSetTitle() {
        Professor prof = new Professor();
        String expected = "Prof. Dr.";
        prof.setTitle(expected);

        String actual = prof.getTitle();

        assertSame("The title of the professor is not the same.", expected, actual);
    }

    /**
     * Tests the getter for the full name.
     */
    @Test
    public void testGetFullName() {
        Professor prof = new Professor();
        String expected = createFullProfessorName(prof);

        String actual = prof.getFullName();

        assertEquals("The full name of the professor is not equal.", expected, actual);
    }

    /**
     * Tests the getter for the full name when the title is null.
     */
    @Test
    public void testGetFullNameTitleIsNull() {
        Professor prof = new Professor();
        prof.setFirstName("Jochen");
        prof.setLastName("Hertle");
        String expected = "Hertle, Jochen";

        String actual = prof.getFullName();

        assertEquals("The full name of the professor is not equal.", expected, actual);
    }

    /**
     * Tests the getter for the full name when the title is empty.
     */
    @Test
    public void testGetFullNameIsEmpty() {
        Professor prof = new Professor();
        prof.setTitle("");
        prof.setFirstName("Jochen");
        prof.setLastName("Hertle");
        String expected = "Hertle, Jochen";

        String actual = prof.getFullName();

        assertEquals("The full name of the professor is not equal.", expected, actual);
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        Professor prof = new Professor();
        createFullProfessorName(prof);
        String expected = "Professor [title=" + prof.getTitle() + ", firstName=" + prof.getFirstName() + ", lastName="
                + prof.getLastName() + "]";

        String actual = prof.toString();

        assertEquals("The toString does not return expected String.", expected, actual);
    }

    private String createFullProfessorName(final Professor prof) {
        String title = "Prof. Dr.";
        String firstName = "Jochen";
        String lastName = "Hertle";
        prof.setTitle(title);
        prof.setFirstName(firstName);
        prof.setLastName(lastName);
        return title + " " + lastName + ", " + firstName;
    }

}
