/**
 *
 */
package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptService;

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
        final ScriptService service = mock(ScriptService.class);
        final ModelMap model = mock(ModelMap.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        ReflectionTestUtils.setField(controller, "scriptsService", service);
        final Collection<Script> scripts = Collections.emptyList();
        when(service.findAll()).thenReturn(scripts);
        when(request.isUserInRole("ROLE_FACHSCHAFTLER")).thenReturn(false);

        final String viewName = controller.showScriptsByLectures(model, request);

        verify(service).findAllPublicScripts();
        verify(model).addAttribute("scripts", scripts);
        assertEquals("The returned view name is not correct", "scripts/list", viewName);
    }

}
