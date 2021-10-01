package org.rapturemain.tcpmessengermessageframework.message.messages;

import lombok.SneakyThrows;
import org.rapturemain.tcpmessengermessageframework.message.base.MessageBaseEntry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Interface of every message
 * <br>
 * ENCODING:
 * <br>
 * | byte4x | - entry id of an message
 * <br>
 * N Base Entries going next (depends on the message)
 * <br>
 * | byte | - boolean if null true (1) else false (0)
 * <br>
 * | [base entry] | - exits, if previous byte is false (0)
 */
public interface Message<T extends Message<T>> {

    int getEntryId();

    @SneakyThrows(value = {NoSuchMethodException.class, IllegalAccessException.class, InstantiationException.class, InvocationTargetException.class})
    default void read(DataInputStream dataInputStream) throws IOException {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            Class<?> type = f.getType();

            if (!MessageBaseEntry.class.isAssignableFrom(type)) {
                continue;
            }

            boolean isNull = dataInputStream.readBoolean();
            if (!isNull) {
                MessageBaseEntry<?> entry = (MessageBaseEntry<?>) type.getConstructor().newInstance();
                entry.read(dataInputStream);
                f.set(this, entry);
            }
        }
    }

    @SneakyThrows(value = {IllegalAccessException.class})
    default void write(DataOutputStream dataOutputStream) throws IOException {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            Class<?> type = f.getType();

            if (!MessageBaseEntry.class.isAssignableFrom(type)) {
                continue;
            }

            MessageBaseEntry<?> entry = (MessageBaseEntry<?>) f.get(this);

            dataOutputStream.writeBoolean(entry == null);

            if (entry != null) {
                entry.write(dataOutputStream);
            }
        }
    }
}
