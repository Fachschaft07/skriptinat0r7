/**
 *
 */
package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import edu.hm.cs.fs.scriptinat0r7.service.LectureService;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.ScriptsController}.
 */
public class ScriptsControllerTest {

    /**
     * Tests if all scripts are properly retrieved and displayed in the view.
     */
    @Test
    public void testGetAllScripts() {
        final ScriptsController controller = new ScriptsController();
        final ModelMap model = mock(ModelMap.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final LectureService lectureServiceMock = mock(LectureService.class);
        ReflectionTestUtils.setField(controller, "lecturesService", lectureServiceMock);
        when(lectureServiceMock.findLecturesWithPublicScript()).thenReturn(Collections.emptySet());
        when(request.isUserInRole("ROLE_FACHSCHAFTLER")).thenReturn(false);

        final String viewName = controller.showScriptsByLectures(model, request);

        verify(lectureServiceMock).findLecturesWithPublicScript();
        verify(model).addAttribute("lectures", Collections.emptySet());
        assertEquals("The returned view name is not correct", "scripts/lecture-list", viewName);
    }

}
