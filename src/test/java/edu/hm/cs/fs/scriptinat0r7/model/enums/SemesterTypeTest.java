package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

/**
 * Test class for the {@code SemesterType}.
 */
public class SemesterTypeTest {

    /**
     * Tests if the enum elements are in correct order.
     */
    @Test
    public void test() {
        EnumVerifier.forClass(SemesterType.class)
            .withEnumElement("WS")
            .withEnumElement("SS")
            .verify();
    }

}
