package edu.hm.cs.fs.scriptinat0r7.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsersServiceTest {

    @Test
    public void testSummerIFAccountForValidness() {
        UsersService service = new UsersService();
        assertTrue(service.isStudentAccount("ifs10263"));
    }

    @Test
    public void testWinterIFAccountForValidness() {
        UsersService service = new UsersService();
        assertTrue(service.isStudentAccount("ifw10263"));
    }

    @Test
    public void testIFAccountFail() {
        UsersService service = new UsersService();
        assertFalse(service.isStudentAccount("definitely not an account"));
    }
}
