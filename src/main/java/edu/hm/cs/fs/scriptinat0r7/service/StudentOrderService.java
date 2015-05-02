package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.controller.ScriptsController;
import edu.hm.cs.fs.scriptinat0r7.exception.PasswordsMissingException;
import edu.hm.cs.fs.scriptinat0r7.exception.ScriptDocumentNotPartOfScriptException;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;
import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.repositories.StudentOrderRepository;

/**
 * A service for business operations on script documents.
 */
@Service
public class StudentOrderService extends AbstractService {

    private static final Logger LOGGER = Logger.getLogger(ScriptsController.class);

    @Autowired
    private StudentOrderRepository studentOrderRepository;

    @Autowired
    private ScriptDocumentService scriptDocumentsService;

    public StudentOrder placeOrder(final Collection<ScriptDocument> documentsToOrder,
            final Script script,
            final Collection<String> passwords) throws IllegalArgumentException, PasswordsMissingException {

        script.setScriptDocuments(scriptDocumentsService.findByScript(script));

        if (!script.getScriptDocuments().containsAll(documentsToOrder)) {
            throw new ScriptDocumentNotPartOfScriptException("Some documents are not part of this script");
        }

        if (!script.isSubmittedCompletely()) {
            throw new IllegalArgumentException("Script is not orderable");
        }

        if (!documentsToOrder.stream().allMatch(doc -> doc.isPublic())) {
            throw new IllegalArgumentException("At least one document is not public");
        }

        final List<ScriptDocument> documentsWithKnownPassword = documentsToOrder.stream()
                .filter(doc -> doc.isOnePasswordMatching(passwords))
                .collect(Collectors.toList());

        final List<ScriptDocument> documentsWithMissingPasswords = new ArrayList<>(documentsToOrder);
        documentsWithMissingPasswords.removeAll(documentsWithKnownPassword);

        if (!documentsWithMissingPasswords.isEmpty()) {
            throw new PasswordsMissingException(documentsWithMissingPasswords, documentsWithKnownPassword);
        }

        final Collection<ScriptDocument> previousOrderedDocuments = scriptDocumentsService.findOrderedDocuments(getCurrentUser());
        if (CollectionUtils.containsAny(previousOrderedDocuments, documentsToOrder)) {
            throw new IllegalArgumentException("Order contains already ordered script");
        }

        final StudentOrder order = new StudentOrder();
        order.setOrderer(getCurrentUser());
        order.setScriptDocuments(documentsToOrder);
        order.setOrderDate(Calendar.getInstance().getTime());
        final StudentOrder persistedOrder = studentOrderRepository.save(order);

        LOGGER.info("placed order");
        LOGGER.info(persistedOrder);

        return persistedOrder;
    }

    public Collection<StudentOrder> getOrdersWithDocumentsOf(final User user) {
        return studentOrderRepository.findByOrderer(user);
    }

    public Collection<StudentOrder> findOrdersNotTransmittedToCopyshop() {
        return studentOrderRepository.findByCopyShopOrderIsNull();
    }

}
