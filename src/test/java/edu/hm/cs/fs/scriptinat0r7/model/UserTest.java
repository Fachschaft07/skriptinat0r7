package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;

/**
 * Test class for the {@code User}.
 */
public class UserTest {

    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(User.class).withRedefinedSubclass(Professor.class).verify();
    }

    /**
     * Tests the getter and setter of the full name.
     */
    @Test
    public void testGetAndSetFullName() {
        User user = new User();
        user.setFirstName("Hans");
        user.setLastName("Glück");
        String expected = "Glück, Hans";

        String actual = user.getFullName();

        assertEquals("The full name of the user is not as expected.", expected, actual);
    }

    /**
     * Tests the getter and setter of the id.
     */
    @Test
    public void testGetAndSetId() {
        User user = new User();
        int expected = 3;

        user.setId(expected);
        int actual = user.getId();

        assertEquals("The id of the user is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the role.
     */
    @Test
    public void testGetAndSetRole() {
        User user = new User();
        Role expected = Role.USER;

        user.setRole(expected);
        Role actual = user.getRole();

        assertSame("The role of the user is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the email.
     *
     * @throws AddressException
     *             when the email address is not valid.
     */
    @Test
    public void testGetAndSetEmail() throws AddressException {
        User user = new User();
        String expected = "test@email.de";

        user.setEmail(expected);
        String actual = user.getEmail();

        assertSame("The email of the user is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the first name.
     */
    @Test
    public void testGetAndSetFirstName() {
        User user = new User();
        String expected = "Hans";

        user.setFirstName(expected);
        String actual = user.getFirstName();

        assertEquals("The first name of the user is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the last name.
     */
    @Test
    public void testGetAndSetLastName() {
        User user = new User();
        String expected = "Glück";

        user.setLastName(expected);
        String actual = user.getLastName();

        assertEquals("The last name of the user is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the faculty id.
     */
    @Test
    public void testGetAndSetFacultyID() {
        User user = new User();
        String expected = "Informatik";

        user.setFacultyID(expected);
        String actual = user.getFacultyID();

        assertEquals("The faculty id of the user is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the student orders.
     */
    @Test
    public void testGetAndSetStudentOrders() {
        User user = new User();
        Set<StudentOrder> expected = Collections.emptySet();

        user.setStudentOrders(expected);
        Set<StudentOrder> actual = user.getStudentOrders();

        assertSame("The student orders of the user are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the student orders.
     */
    @Test
    public void testAddStudentOrder() {
        User user = new User();
        StudentOrder order = new StudentOrder();

        user.addStudentOrder(order);

        assertEquals("The set of student orders has not 1 element.", 1, user.getStudentOrders().size());
    }

    /**
     * Tests the add method of the student orders when there is already an existing set.
     */
    @Test
    public void testAddStudentOrderExistingSet() {
        User user = new User();
        StudentOrder order = new StudentOrder();
        user.setStudentOrders(new HashSet<StudentOrder>());

        user.addStudentOrder(order);

        assertEquals("The set of student orders has not 1 element.", 1, user.getStudentOrders().size());
    }

    /**
     * Tests the remove method of the student orders.
     */
    @Test
    public void testRemoveStudentOrder() {
        User user = new User();
        StudentOrder order = new StudentOrder();
        user.addStudentOrder(order);
        assertEquals("The set of student orders has not 1 element.", 1, user.getStudentOrders().size());

        user.removeStudentOrder(order);
        assertEquals("The set of student orders has not 0 elements.", 0, user.getStudentOrders().size());
    }

    /**
     * Tests the toString method.
     *
     * @throws AddressException
     *             when the email address is invalid.
     */
    @Test
    public void testToString() throws AddressException {
        User user = new User();
        user.setId(3);
        user.setRole(Role.FACHSCHAFTLER);
        user.setEmail("beispiel@fs.cs.hm.edu");
        user.setFirstName("Hans");
        user.setLastName("Glück");
        String expected = "User [id=3, role=FACHSCHAFTLER, email=beispiel@fs.cs.hm.edu, firstName=Hans, lastName=Glück]";

        String actual = user.toString();

        assertEquals("The toString does not return expected String.", expected, actual);
    }

}
