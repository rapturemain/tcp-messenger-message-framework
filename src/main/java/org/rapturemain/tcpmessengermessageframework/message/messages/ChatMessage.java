package org.rapturemain.tcpmessengermessageframework.message.messages;

import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;

public interface ChatMessage<T extends ChatMessage<T>> extends Message<T> {
    TimestampEntry getTimestamp();
    StringEntry getSenderName();

    void setTimestamp(TimestampEntry timestampEntry);
    void setSenderName(StringEntry stringEntry);
}
