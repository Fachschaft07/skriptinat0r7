package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

public class SemesterTypeTest {

    @Test
    public void test() {
        EnumVerifier.forClass(SemesterType.class)
            .withEnumElement("WS")
            .withEnumElement("SS")
            .verify();
    }

}
