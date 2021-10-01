package org.rapturemain.tcpmessengermessageframework.message.messages.request;

import org.rapturemain.tcpmessengermessageframework.message.messages.SystemMessage;

public class PingRequest implements SystemMessage<PingRequest> {

    private static final int ENTRY_ID = 5;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
