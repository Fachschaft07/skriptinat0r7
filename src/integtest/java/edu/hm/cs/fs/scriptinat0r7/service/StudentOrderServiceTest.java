package edu.hm.cs.fs.scriptinat0r7.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import edu.hm.cs.fs.scriptinat0r7.exception.PasswordsMissingException;
import edu.hm.cs.fs.scriptinat0r7.exception.ScriptDocumentNotPartOfScriptException;
import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptDocumentRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.StudentOrderRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/test-beans.xml")
public class StudentOrderServiceTest {

    // CHECKSTYLE:OFF
    // TODO: remove checkstyle comment, upgrade checkstyle and configure VisibilityModifier ignoreAnnotationCanonicalNames
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    // CHECKSTYLE:ON

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private StudentOrderRepository studentOrderRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private ScriptDocumentRepository scriptDocumentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @DirtiesContext
    @Test
    public void testOrder() throws IllegalArgumentException, PasswordsMissingException {
        // given
        final long orderCountBefore = studentOrderRepository.count();
        mockLecture();
        final Script script = mockScript();
        final ScriptDocument scriptDocument = mockScriptDocumentWithScript(script);
        final User user = mockUser();
        mockSecurityContext(user);

        // when
        studentOrderService.placeOrder(Arrays.asList(scriptDocument), script, Arrays.asList());

        // then everything should be fine
        assertThat(studentOrderRepository.count(), equalTo(orderCountBefore + 1));
        assertThat(studentOrderService.getOrdersWithDocumentsOf(user).size(), equalTo(1));
    }

    private void mockSecurityContext(final User user) {
        final SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(user, null));
        SecurityContextHolder.setContext(securityContext);
    }

    private User mockUser() {
        final User user = new User();
        user.setRole(Role.USER);
        user.setFacultyID("ifs10000");
        return userRepository.save(user);
    }

    @DirtiesContext
    @Test
    public void testNullArguments() throws IllegalArgumentException, PasswordsMissingException {
        // given
        thrown.expect(InvalidDataAccessApiUsageException.class);

        // when
        studentOrderService.placeOrder(null, null, Arrays.asList());
    }


    @DirtiesContext
    @Test
    public void testExceptionOnBadScriptDocumentOrder() throws IllegalArgumentException, PasswordsMissingException {
        // given
        mockLecture();
        final Script script = mockScript();
        thrown.expect(ScriptDocumentNotPartOfScriptException.class);

        // when
        studentOrderService.placeOrder(Arrays.asList(new ScriptDocument()), script, Arrays.asList());
    }

    private ScriptDocument mockScriptDocumentWithScript(final Script script) {
        final ScriptDocument scriptDocument = new ScriptDocument();
        scriptDocument.setReviewState(ReviewState.FACHSCHAFTLERAPPROVED);
        scriptDocument.addScript(script);
        scriptDocument.setFile(new byte[] {});
        scriptDocument.setFilename("test");
        scriptDocument.setHashvalue(329L);
        scriptDocumentRepository.save(scriptDocument);
        return scriptDocument;
    }

    private Script mockScript() {
        final Script script = new Script();
        script.setName("script");
        script.setLectures(Lists.newArrayList(lectureRepository.findAll()));
        script.setSubmittedCompletely(true);
        return scriptRepository.save(script);
    }

    private Lecture mockLecture() {
        final Lecture lecture = new Lecture();
        lecture.setName("lecture");
        return lectureRepository.save(lecture);
    }

}
