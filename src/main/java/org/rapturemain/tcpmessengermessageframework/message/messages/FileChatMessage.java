package org.rapturemain.tcpmessengermessageframework.message.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.RawBytesEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileChatMessage implements ChatMessage<FileChatMessage> {

    private static final int ENTRY_ID = 4;

    private TimestampEntry timestamp;
    private StringEntry senderName;

    private StringEntry fileName;
    private RawBytesEntry bytes;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}

