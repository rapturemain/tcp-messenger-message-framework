package org.rapturemain.tcpmessengermessageframework.message.base;

import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface MessageBaseEntry<T extends MessageBaseEntry<T>> {

    int getEntryId();

    void read(@NotNull DataInputStream dataInputStream) throws IOException;

    void write(@NotNull DataOutputStream dataOutputStream) throws IOException;
}
