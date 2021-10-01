package org.rapturemain.tcpmessengermessageframework.message.messages.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageWithTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisteredMessage implements
        SystemMessage<UserRegisteredMessage>,
        MessageWithTimestamp<UserRegisteredMessage> {
    private final static int ENTRY_ID = 9;

    private TimestampEntry timestamp;
    private StringEntry name;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
