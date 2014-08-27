package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.testfixtures.EnumVerifier;

public class RoleTest {

    @Test
    public void test() {
        EnumVerifier.forClass(Role.class)
            .withEnumElement("REGULAR")
            .withEnumElement("FACHSCHAFTLER")
            .withEnumElement("PROFESSOR")
            .withEnumElement("LOCKED")
            .withEnumElement("AUTHOR")
            .verify();
    }

}