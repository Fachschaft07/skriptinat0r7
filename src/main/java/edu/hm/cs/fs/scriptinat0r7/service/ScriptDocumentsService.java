package edu.hm.cs.fs.scriptinat0r7.service;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.pdf.PdfHelper;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptDocumentsRepository;

/**
 * A service for business operations on script documents.
 */
@Service
public class ScriptDocumentsService {

    @Autowired
    private ScriptDocumentsRepository scriptDocuments;

    /**
     * Persists a given script document.
     * @param script the script which shall contain this document.
     * @param sortNumber the sort number of the new document.
     * @param file the script document.
     * @return the persisted script document.
     * @throws IOException if io errors happen while reading the file contents.
     */
    public ScriptDocument create(final Script script, final int sortNumber,
            final MultipartFile file) throws IOException {
        final ScriptDocument document = new ScriptDocument();
        document.setFile(file.getBytes());
        document.setHashvalue(document.computeHashvalue());
        document.setFilename(file.getOriginalFilename());
        document.setReviewState(ReviewState.LOCKED);
        document.setScript(script);
        document.setSortnumber(sortNumber);
        document.setScript(script);
        // TODO: check if is pdf
        // TODO: check if needs pw
        document.setPasswordMissing(true);
        return save(document);
    }

    /**
     * Finds the script documents of a given script.
     * @param script the script whose documents shall be retrieved.
     * @return a collection of ordered ScriptDocuments.
     */
    @Transactional
    public List<ScriptDocument> findByScript(final Script script) {
        return scriptDocuments.findByScriptOrderBySortnumberAsc(script);
    }

    /**
     * Tries given passwords on all script documents who are not yet decrypted.
     * @param script the script, whose documents shall be tested.
     * @param passwordsToTry the passwords that shall be tried.
     * @return documents, where passwords are still missing. if all can be decrypted this is an empty list.
     */
    public List<ScriptDocument> tryPasswordsOnScriptDocumentsWithMissingPassword(final Script script, final Collection<String> passwordsToTry) {
        final List<ScriptDocument> documentsWherePasswordStillMissing = new LinkedList<>();

        for (ScriptDocument currentDocument : scriptDocuments.findByScriptAndIsPasswordMissingTrue(script)) {
            try {
                final String passwordThatDecryptsThisDocument = PdfHelper.findCorrectPassword(currentDocument.getFile(), passwordsToTry);
                currentDocument.setPassword(passwordThatDecryptsThisDocument);
                currentDocument.setPasswordMissing(false);
                save(currentDocument);
            } catch (IllegalArgumentException | IOException e) {
                documentsWherePasswordStillMissing.add(currentDocument);
            }
        }

        return documentsWherePasswordStillMissing;
    }

    /**
     * Saves a given script document.
     * @param document the document to persist.
     * @return the saved document, which could e.g. have a changed id.
     */
    public ScriptDocument save(final ScriptDocument document) {
        return scriptDocuments.save(document);
    }

    /**
     * Updates the order of the given documents to match the order given in orderedDocumentHashes.
     * @param orderedDocumentHashes the ordered document hashes.
     * @param documents the documents whose order shall be updated.
     */
    @Transactional
    public void updateDocumentOrder(final List<Long> orderedDocumentHashes,
            final List<ScriptDocument> documents) {
        for (final ScriptDocument document : documents) {
            boolean hasChanged = false;
            for (int i = 0; i < orderedDocumentHashes.size(); i++) {
                final Long documentHash = orderedDocumentHashes.get(i);
                if (Objects.equals(documentHash, document.getHashvalue())) {
                    document.setSortnumber(i);
                    hasChanged = true;
                    scriptDocuments.save(document);
                    break;
                }
            }
            if (!hasChanged) {
                throw new IllegalArgumentException("at least one document has not been assigned a sortnumber. this is a illegal condition, as it could cause duplicate sortnumbers");
            }
        }
    }

}
