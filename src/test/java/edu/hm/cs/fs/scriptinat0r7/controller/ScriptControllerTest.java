/**
 *
 */
package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.ScriptController}.
 *
 * @author Maximilian Götz
 *
 */
public class ScriptControllerTest {
    
    @Test
    public void testGetAllScripts() throws Exception {
        ScriptController controller = new ScriptController();
        ScriptRepository repo = mock(ScriptRepository.class);
        ModelMap model = mock(ModelMap.class);
        ReflectionTestUtils.setField(controller, "scripts", repo);
        Iterable<Script> scripts = Collections.emptyList();
        when(repo.findAll()).thenReturn(scripts);
        
        String viewName = controller.getAllScripts(model);
        
        verify(repo).findAll();
        verify(model).addAttribute("scripts", scripts);
        assertEquals("The returned view name is not correct", "allScripts", viewName);
    }

}