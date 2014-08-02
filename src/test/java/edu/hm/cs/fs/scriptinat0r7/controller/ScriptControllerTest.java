/**
 *
 */
package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.ui.ModelMap;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.ScriptController}
 *
 * @author Maximilian Götz
 *
 */
public class ScriptControllerTest {

    /**
     * Test method for
     * {@link edu.hm.cs.fs.scriptinat0r7.controller.ScriptController#example(org.springframework.ui.ModelMap)} .
     */
    @Test
    public void testExample() {
        ScriptController controller = new ScriptController();
        ModelMap model = mock(ModelMap.class);

        String result = controller.example(model);

        assertEquals("example", result);
    }
}