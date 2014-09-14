package edu.hm.cs.fs.scriptinat0r7.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.zip.CRC32;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptDocumentsRepository;

@Service
public class ScriptDocumentsService {

    @Autowired
    ScriptDocumentsRepository scriptDocuments;

    public ScriptDocument save(final Script script, final int sortNumber,
            final MultipartFile file) throws IOException {
        final ScriptDocument document = new ScriptDocument();
        document.setFile(file.getBytes());
        document.setHashvalue(computeHashvalue(document));
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

    @Transactional
    public List<ScriptDocument> findByScript(final Script script) {
        return scriptDocuments.findByScriptOrderBySortnumberAsc(script);
    }

    public List<ScriptDocument> tryPasswordsOnScriptDocumentsWithMissingPassword(final Script script, Collection<String> passwordsToTry) {
        final List<ScriptDocument> documentsWherePasswordStillMissing = new LinkedList<>();

        for (ScriptDocument currentDocument : scriptDocuments.findByScriptAndIsPasswordMissingTrue(script)) {
            try {
                final String passwordThatDecryptsThisDocument = tryPasswordsForDocument(passwordsToTry, currentDocument);
                currentDocument.setPassword(passwordThatDecryptsThisDocument);
                currentDocument.setPasswordMissing(false);
                save(currentDocument);
            } catch (IllegalArgumentException | IOException e) {
                documentsWherePasswordStillMissing.add(currentDocument);
            }
        }

        return documentsWherePasswordStillMissing;
    }

    public String tryPasswordsForDocument(final Collection<String> passwords,
            final ScriptDocument scriptDocument) throws IOException, IllegalArgumentException {
        PDFParser pdf = null;
        try {
            final ByteArrayInputStream bytes = new ByteArrayInputStream(scriptDocument.getFile());
            pdf = new PDFParser(bytes);
            pdf.parse();
            final PDDocument pdfDocument = pdf.getPDDocument();

            if (!pdfDocument.isEncrypted()) {
                return StringUtils.EMPTY;
            } else {
                for (String password : passwords) {
                    try {
                        StandardDecryptionMaterial passwordDecrypter = new StandardDecryptionMaterial(password);
                        pdfDocument.openProtection(passwordDecrypter);
                        return password;
                    } catch (Exception e) {
                        // was wrong password, try next...
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(pdf.getDocument());
            pdf.clearResources();
        }

        throw new IllegalArgumentException("no password is able to decrypt the document");
    }



    public long computeHashvalue(final ScriptDocument script) {
        final CRC32 crc = new CRC32();
        crc.update(script.getFile());
        return crc.getValue();
    }

    public ScriptDocument save(final ScriptDocument document) {
        return scriptDocuments.save(document);
    }

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
