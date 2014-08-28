package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

/**
 * Test class for the {@code ReviewState}.
 */
public class ReviewStateTest {

    /**
     * Tests if the enum elements are in correct order.
     */
    @Test
    public void test() {
        EnumVerifier.forClass(ReviewState.class)
            .withEnumElement("LOCKED")
            .withEnumElement("FACHSCHAFTLERAPPROVED")
            .withEnumElement("PROFESSORAPPROVED")
            .withEnumElement("DELETED")
            .verify();
    }

}
