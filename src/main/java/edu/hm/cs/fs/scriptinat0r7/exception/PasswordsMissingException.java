package edu.hm.cs.fs.scriptinat0r7.exception;

import java.util.Collection;

import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;

public class PasswordsMissingException extends Exception {

    private static final long serialVersionUID = 4121094564583751194L;
    private final Collection<ScriptDocument> documentsWithMissingPasswords;
    private final Collection<ScriptDocument> documentsWithKnownPassword;

    public PasswordsMissingException(final Collection<ScriptDocument> documentsWithMissingPasswords, final Collection<ScriptDocument> documentsWithKnownPassword) {
        this.documentsWithMissingPasswords = documentsWithMissingPasswords;
        this.documentsWithKnownPassword = documentsWithKnownPassword;
    }

    public Collection<ScriptDocument> getDocumentsWithMissingPasswords() {
        return documentsWithMissingPasswords;
    }

    public Collection<ScriptDocument> getDocumentsWithKnownPassword() {
        return documentsWithKnownPassword;
    }
}
