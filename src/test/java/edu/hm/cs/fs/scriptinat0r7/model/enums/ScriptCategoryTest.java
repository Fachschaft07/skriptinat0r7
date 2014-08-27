package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

public class ScriptCategoryTest {

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
