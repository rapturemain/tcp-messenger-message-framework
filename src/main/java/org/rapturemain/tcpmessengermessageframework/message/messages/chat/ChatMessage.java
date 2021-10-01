package org.rapturemain.tcpmessengermessageframework.message.messages.chat;

import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageWithTimestamp;

public interface ChatMessage<T extends ChatMessage<T>> extends MessageWithTimestamp<T> {
    StringEntry getSenderName();
    void setSenderName(StringEntry stringEntry);
}
