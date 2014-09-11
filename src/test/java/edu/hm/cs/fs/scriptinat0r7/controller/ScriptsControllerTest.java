/**
 *
 */
package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

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
        final ScriptRepository repo = mock(ScriptRepository.class);
        final ModelMap model = mock(ModelMap.class);
        ReflectionTestUtils.setField(controller, "scripts", repo);
        final Collection<Script> scripts = Collections.emptyList();
        when(repo.findAll()).thenReturn(scripts);

        final String viewName = controller.showApprovedScripts(model);

        verify(repo).findByReviewState(ReviewState.FACHSCHAFTLERAPPROVED, ReviewState.PROFESSORAPPROVED);
        verify(model).addAttribute("scripts", scripts);
        assertEquals("The returned view name is not correct", "scripts/list", viewName);
    }

}
