package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

/**
 * Test class for the {@code Role}.
 */
public class RoleTest {

    /**
     * Tests if the enum elements are in correct order.
     */
    @Test
    public void test() {
        EnumVerifier.forClass(Role.class)
            .withEnumElement("USER")
            .withEnumElement("FACHSCHAFTLER")
            .withEnumElement("PROFESSOR")
            .withEnumElement("LOCKED")
            .withEnumElement("AUTHOR")
            .verify();
    }

}
