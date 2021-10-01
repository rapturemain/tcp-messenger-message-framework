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
public class RawBytesEntry implements MessageBaseEntry<RawBytesEntry> {
    private static final int ENTRY_ID = 2;

    private byte[] bytes;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }

    @Override
    public void read(@NotNull DataInputStream dataInputStream) throws IOException {
        int size = dataInputStream.readInt();
        bytes = dataInputStream.readNBytes(size);
    }

    @Override
    public void write(@NotNull DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
    }
}
