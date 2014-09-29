package edu.hm.cs.fs.scriptinat0r7.service;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Provides tests for the UsersService.
 */
public class UsersServiceTest {

    /**
     * Test a summer student account for validness.
     */
    @Test
    public void testSummerIFAccountForValidness() {
        UsersService service = new UsersService();
        assertTrue("ifs10263 should be a valid student user name", service.isStudentAccount("ifs10263"));
    }

    /**
     * Test a winter student account for validness.
     */
    @Test
    public void testWinterIFAccountForValidness() {
        UsersService service = new UsersService();
        assertTrue("ifw10263 should be a valid student user name", service.isStudentAccount("ifw10263"));
    }

    /**
     * Assure that a non student looking account is rejected as student account.
     */
    @Test
    public void testIFAccountFail() {
        UsersService service = new UsersService();
        assertFalse("'definitely not an account' should not be treated as a legal ifw / ifs account name", service.isStudentAccount("definitely not an account"));
    }
}
