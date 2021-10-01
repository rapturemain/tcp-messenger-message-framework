package org.rapturemain.tcpmessengermessageframework.message.messages.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleChatMessage implements ChatMessage<SimpleChatMessage> {
    private static final int ENTRY_ID = 0;

    private TimestampEntry timestamp;
    private StringEntry senderName;
    private StringEntry text;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
