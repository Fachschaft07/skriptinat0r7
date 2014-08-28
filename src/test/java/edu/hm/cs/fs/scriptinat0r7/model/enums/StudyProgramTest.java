package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

/**
 * Test class for the {@code StudyProgram}.
 */
public class StudyProgramTest {

    /**
     * Tests if the enum elements are in correct order.
     */
    @Test
    public void test() {
        EnumVerifier.forClass(StudyProgram.class)
            .withEnumElement("MASTER")
            .withEnumElement("INFORMATIK")
            .withEnumElement("WIRTSCHAFTSINFORMATIK")
            .withEnumElement("GEOTELEMATIK")
            .withEnumElement("SCIENTIFIC_COMPUTING")
            .verify();
    }

}
