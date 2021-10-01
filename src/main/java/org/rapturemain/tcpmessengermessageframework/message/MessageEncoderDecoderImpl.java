package org.rapturemain.tcpmessengermessageframework.message;

import lombok.SneakyThrows;
import org.rapturemain.tcpmessengermessageframework.message.messages.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageFormatException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MessageEncoderDecoderImpl implements MessageEncoderDecoder {

    private final HashMap<Integer, Class<Message<?>>> messages = new HashMap<>();

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public void start() {
        for (Class<?> clazz : MessageEncoderDecoder.messages) {
            messages.put(newInstance((Class<Message<?>>) clazz).getEntryId(), (Class<Message<?>>) clazz);
        }
    }

    @Override
    public void encode(@NotNull Message<?> message, @NotNull DataOutputStream dataOutputStream) throws IOException {
        try {
            dataOutputStream.writeInt(message.getEntryId());
            message.write(dataOutputStream);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new MessageFormatException(e);
        }
    }

    @Override
    @Nullable
    public Message<?> decode(@NotNull DataInputStream dataInputStream) throws IOException {
        if (dataInputStream.available() < 1) {
            return null;
        }
        try {
            int entryId = dataInputStream.readInt();
            Message<?> message = newInstance(messages.get(entryId));
            message.read(dataInputStream);
            return message;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new MessageFormatException(e);
        }
    }

    @SneakyThrows
    private Message<?> newInstance(Class<Message<?>> clazz) {
        return clazz.getDeclaredConstructor().newInstance();
    }
}
