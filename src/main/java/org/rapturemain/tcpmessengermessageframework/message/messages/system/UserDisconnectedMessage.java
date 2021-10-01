package org.rapturemain.tcpmessengermessageframework.message.messages.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageWithTimestamp;
import org.rapturemain.tcpmessengermessageframework.message.messages.SystemMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDisconnectedMessage implements
        SystemMessage<UserDisconnectedMessage>,
        MessageWithTimestamp<UserDisconnectedMessage> {
    private final static int ENTRY_ID = 8;

    private TimestampEntry timestamp;
    private StringEntry name;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
