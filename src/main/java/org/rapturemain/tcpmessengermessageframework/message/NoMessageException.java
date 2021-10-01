package org.rapturemain.tcpmessengermessageframework.message;

public class NoMessageException extends RuntimeException {
    public NoMessageException(int messageId) {
        super("No message for " + Integer.toString(messageId));
    }
}
