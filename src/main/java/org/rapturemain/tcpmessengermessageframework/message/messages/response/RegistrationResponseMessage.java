package org.rapturemain.tcpmessengermessageframework.message.messages.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.BooleanEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;
import org.rapturemain.tcpmessengermessageframework.message.base.TimestampEntry;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageWithTimestamp;
import org.rapturemain.tcpmessengermessageframework.message.messages.SystemMessage;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseMessage implements
        SystemMessage<RegistrationResponseMessage>,
        MessageWithTimestamp<RegistrationResponseMessage> {

    private final static int ENTRY_ID = 3;

    private TimestampEntry timestamp;
    private BooleanEntry success;
    private StringEntry message;
    private StringEntry name;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
