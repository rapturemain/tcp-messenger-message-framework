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
 * | byte2x | - size of an byte array
 * <br>
 * | byte[] | - String encoded by modified UTF-8 encoding
 * <br>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/DataInput.html#modified-utf-8">Modified UTF-8</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringEntry implements MessageBaseEntry<StringEntry> {
    private static final int ENTRY_ID = 1;

    private String string;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }

    @Override
    public void read(@NotNull DataInputStream dataInputStream) throws IOException {
        string = dataInputStream.readUTF();
    }

    @Override
    public void write(@NotNull DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(string);
    }
}
