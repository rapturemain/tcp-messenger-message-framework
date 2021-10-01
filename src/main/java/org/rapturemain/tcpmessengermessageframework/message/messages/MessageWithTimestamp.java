package org.rapturemain.tcpmessengermessageframework.message.messages;

import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;

public interface MessageWithTimestamp<T extends MessageWithTimestamp<T>> extends Message<T> {
    TimestampEntry getTimestamp();
    void setTimestamp(TimestampEntry timestampEntry);
}
