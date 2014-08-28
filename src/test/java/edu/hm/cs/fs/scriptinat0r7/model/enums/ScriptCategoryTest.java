package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

/**
 * Test class for the {@code ScriptCategory}.
 */
public class ScriptCategoryTest {

    /**
     * Tests if the enum elements are in correct order.
     */
    @Test
    public void test() {
        EnumVerifier.forClass(ScriptCategory.class)
            .withEnumElement("LECTURE_SCRIPT")
            .withEnumElement("EXERCISE")
            .withEnumElement("STUDENT_NOTE")
            .withEnumElement("SUMMARY")
            .verify();
    }

}
