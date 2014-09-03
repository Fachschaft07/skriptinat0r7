package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.ModelMap;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.AboutController}.
 *
 * @author Maximilian Götz
 *
 */
public class AboutControllerTest {

    /**
     * Test method for
     * {@link edu.hm.cs.fs.scriptinat0r7.controller.AboutController#about(org.springframework.ui.ModelMap)} .
     */
    @Test
    public void testAbout() {
        final AboutController controller = new AboutController();
        final ModelMap model = mock(ModelMap.class);

        final String result = controller.about(model);

        assertEquals("The view name is not correct.", "about/about", result);
        verify(model).addAttribute(eq("contributors"), any(List.class));
        verify(model).addAttribute(eq("year"), any(Calendar.class));
    }

}
