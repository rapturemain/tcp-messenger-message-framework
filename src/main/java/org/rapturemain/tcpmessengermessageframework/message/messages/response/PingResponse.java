package org.rapturemain.tcpmessengermessageframework.message.messages.response;

import org.rapturemain.tcpmessengermessageframework.message.messages.SystemMessage;

public class PingResponse implements SystemMessage<PingResponse> {

    private static final int ENTRY_ID = 6;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
