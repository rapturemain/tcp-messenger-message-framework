package org.rapturemain.tcpmessengermessageframework.message.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimestampEntry implements MessageBaseEntry<TimestampEntry> {
    private static final int ENTRY_ID = 0;

    private Long timestamp;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }

    @Override
    public void read(@NotNull DataInputStream dataInputStream) throws IOException {
        timestamp = dataInputStream.readLong();
    }

    @Override
    public void write(@NotNull DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeLong(timestamp);
    }
}
