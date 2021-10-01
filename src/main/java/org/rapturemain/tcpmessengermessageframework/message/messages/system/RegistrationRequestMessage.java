package org.rapturemain.tcpmessengermessageframework.message.messages.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rapturemain.tcpmessengermessageframework.message.base.StringEntry;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestMessage implements SystemMessage<RegistrationRequestMessage> {

    private final static int ENTRY_ID = 2;

    private StringEntry name;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
