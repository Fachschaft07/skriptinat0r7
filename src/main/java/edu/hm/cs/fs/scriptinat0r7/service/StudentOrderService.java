package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.exception.PasswordsMissingException;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;
import edu.hm.cs.fs.scriptinat0r7.repositories.StudentOrderRepository;

/**
 * A service for business operations on script documents.
 */
@Service
public class StudentOrderService {

    @Autowired
    private StudentOrderRepository studentOrderRepository;

    @Autowired
    private ScriptDocumentService scriptDocumentsService;

    public StudentOrder placeOrder(final Collection<ScriptDocument> documentsToOrder, final Script script, final Collection<String> passwords) throws IllegalArgumentException, PasswordsMissingException {

        script.setScriptDocuments(scriptDocumentsService.findByScript(script));

        if ( ! script.getScriptDocuments().containsAll(documentsToOrder)) {
            throw new IllegalArgumentException("Some documents are not part of this script");
        }

        if ( ! script.isSubmittedCompletely()) {
            throw new IllegalArgumentException("Script is not orderable");
        }

        if ( ! documentsToOrder.stream().allMatch(doc -> doc.isPublic())) {
            throw new IllegalArgumentException("At least one document is not public");
        }

        final List<ScriptDocument> documentsWithKnownPassword = documentsToOrder.stream()
                .filter(doc -> doc.isOnePasswordMatching(passwords))
                .collect(Collectors.toList());

        final List<ScriptDocument> documentsWithMissingPasswords = new ArrayList<>(documentsToOrder);
        documentsWithMissingPasswords.removeAll(documentsWithKnownPassword);

        if ( ! documentsWithMissingPasswords.isEmpty()) {
            throw new PasswordsMissingException(documentsWithMissingPasswords, documentsWithKnownPassword);
        }

        return new StudentOrder();
    }

}
