package edu.hm.cs.fs.scriptinat0r7.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.hm.cs.fs.scriptinat0r7.exception.PasswordsMissingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/test-beans.xml")
public class StudentOrderServiceTest {

    @Autowired
    private StudentOrderService studentOrderService;

    @DirtiesContext
    @Test
    public void testSomething() throws IllegalArgumentException, PasswordsMissingException {
        studentOrderService.placeOrder(null, null, null);
    }

}
