package org.rapturemain.tcpmessengermessageframework.message.messages;

import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;

public interface ChatMessage<T extends ChatMessage<T>> extends MessageWithTimestamp<T> {
    StringEntry getSenderName();

    void setSenderName(StringEntry stringEntry);
}
