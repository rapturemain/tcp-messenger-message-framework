package org.rapturemain.tcpmessengermessageframework.message.messages.system;

public class ConnectionResetMessage implements SystemMessage<ConnectionResetMessage> {

    private static final int ENTRY_ID = 1;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
