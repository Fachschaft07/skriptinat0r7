package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.ModelMap;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.HomeController}.
 *
 * @author Maximilian Götz
 *
 */
public class HomeControllerTest {

    /**
     * Test method for
     * {@link edu.hm.cs.fs.scriptinat0r7.controller.HomeController#about(org.springframework.ui.ModelMap)} .
     */
    @Test
    public void testAbout() {
        HomeController controller = new HomeController();
        ModelMap model = mock(ModelMap.class);

        String result = controller.about(model);

        assertEquals("about", result);
        verify(model).addAttribute(eq("contributors"), any(List.class));
        verify(model).addAttribute(eq("year"), any(Calendar.class));
    }

}
