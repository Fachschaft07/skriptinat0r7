package edu.hm.cs.fs.scriptinat0r7.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;

/**
 * Test class for {@link edu.hm.cs.fs.scriptinat0r7.controller.ProfessorsController}.
 */
public class ProfessorsControllerTest {

    /**
     * Tests if all scripts are properly retrieved and displayed in the view.
     */
    @Test
    public void testEditProfessorForm() {
        final ProfessorsController controller = new ProfessorsController();
        final ModelMap model = mock(ModelMap.class);
        final Professor professor = new Professor();
        final LectureService lecturesServiceMock = mock(LectureService.class);
        ReflectionTestUtils.setField(controller, "lecturesService", lecturesServiceMock);
        final String viewName = controller.editProfessorForm(model, professor);

        verify(model).addAttribute("professor", professor);
        assertEquals("The returned view name is not correct", "professors/edit", viewName);
    }

}
