package org.rapturemain.tcpmessengermessageframework.message.messages.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageWithTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserConnectedMessage implements
        SystemMessage<UserConnectedMessage>,
        MessageWithTimestamp<UserConnectedMessage> {
    private final static int ENTRY_ID = 7;

    private TimestampEntry timestamp;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
