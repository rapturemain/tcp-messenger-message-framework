package org.rapturemain.tcpmessengermessageframework.message.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * ENCODING
 * <br>
 * | byte | - boolean. 1 if true and 0 if false
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooleanEntry implements MessageBaseEntry<BooleanEntry> {
    private static final int ENTRY_ID = 3;

    private Boolean value;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }

    @Override
    public void read(@NotNull DataInputStream dataInputStream) throws IOException {
        value = dataInputStream.readBoolean();
    }

    @Override
    public void write(@NotNull DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeBoolean(value);
    }
}
