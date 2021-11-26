package org.rapturemain.tcpmessengermessageframework.message;

import lombok.SneakyThrows;
import org.rapturemain.tcpmessengermessageframework.message.messages.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rapturemain.tcpmessengermessageframework.message.messages.MessageFormatException;

import java.io.*;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class MessageEncoderDecoderImpl implements MessageEncoderDecoder {

    private final static int SIZE_BYTE_COUNT = 4;

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
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream wrapped = new DataOutputStream(byteArrayOutputStream);
            wrapped.writeInt(message.getEntryId());
            message.write(wrapped);

            dataOutputStream.writeInt(wrapped.size());
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
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
            int size = dataInputStream.readInt();
            int entryId = dataInputStream.readInt();
            Message<?> message = newInstance(getClassById(entryId));
            message.read(dataInputStream);
            return message;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new MessageFormatException(e);
        }
    }

    @Override
    @Nullable
    public Message<?> decode(byte[] bytes) throws IOException {
        if (bytes.length < SIZE_BYTE_COUNT) {
            return null;
        }
        int size = 0;
        for (int i = 0; i < SIZE_BYTE_COUNT; i++) {
            size += bytes[i] << (Byte.SIZE * (SIZE_BYTE_COUNT - i - 1));
        }
        if (size + SIZE_BYTE_COUNT > bytes.length) {
            return null;
        }

        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        return decode(dataInputStream);
    }

    @Override
    public boolean canBeDecoded(byte[] bytes, @NotNull ByteBuffer byteBuffer) {
        int size = getMessageSize(bytes, byteBuffer);
        return canBeDecoded(size, bytes, byteBuffer);
    }

    @Override
    public byte[] merge(byte[] bytes, @NotNull ByteBuffer byteBuffer) {
        int size = getMessageSize(bytes, byteBuffer);
        byte[] merged;

        if (!canBeDecoded(size, bytes, byteBuffer)) {
            merged = new byte[bytes.length + byteBuffer.remaining()];
            System.arraycopy(bytes, 0, merged, 0, bytes.length);
            byteBuffer.get(merged, bytes.length, byteBuffer.remaining());
            byteBuffer.mark();
            return merged;
        }

        merged = new byte[size + SIZE_BYTE_COUNT];
        System.arraycopy(bytes, 0, merged, 0, bytes.length);
        byteBuffer.get(merged, bytes.length, size + SIZE_BYTE_COUNT - bytes.length);
        byteBuffer.mark();
        return merged;
    }

    @SneakyThrows
    private Message<?> newInstance(Class<Message<?>> clazz) {
        return clazz.getDeclaredConstructor().newInstance();
    }

    private Class<Message<?>> getClassById(int entryId) {
        Class<Message<?>> clazz = messages.get(entryId);
        if (clazz == null) {
            throw new NoMessageException(entryId);
        }
        return clazz;
    }

    private boolean canBeDecoded(int size, byte[] bytes, @NotNull ByteBuffer byteBuffer) {
        if (size == -1) {
            return false;
        }
        return size + SIZE_BYTE_COUNT <= bytes.length + byteBuffer.remaining();
    }

    private int getMessageSize(byte[] bytes, @NotNull ByteBuffer byteBuffer) {
        byteBuffer.reset();
        int byteBufferStartPos = byteBuffer.position();

        int[] sizeBytes = new int[SIZE_BYTE_COUNT];
        int i = 0;
        for (; i < SIZE_BYTE_COUNT && i < bytes.length; i++) {
            sizeBytes[i] = ((int) bytes[i]) & 0xFF;
        }
        try {
            for (; i < SIZE_BYTE_COUNT; i++) {
                sizeBytes[i] = ((int) byteBuffer.get()) & 0xFF;
            }
        } catch (BufferUnderflowException e) {
            return -1;
        } finally {
            byteBuffer.position(byteBufferStartPos);
        }

        int size = 0;
        for (i = SIZE_BYTE_COUNT - 1; i >= 0; i--) {
            size |= sizeBytes[SIZE_BYTE_COUNT - i - 1] << (Byte.SIZE * i);
        }

        return size;
    }
}
