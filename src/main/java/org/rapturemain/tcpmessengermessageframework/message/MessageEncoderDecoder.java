package org.rapturemain.tcpmessengermessageframework.message;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rapturemain.tcpmessengermessageframework.message.messages.Message;
import org.rapturemain.tcpmessengermessageframework.message.messages.chat.FileChatMessage;
import org.rapturemain.tcpmessengermessageframework.message.messages.chat.SimpleChatMessage;
import org.rapturemain.tcpmessengermessageframework.message.messages.system.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface MessageEncoderDecoder {
    Class<?>[] messages = {
            SimpleChatMessage.class,
            ConnectionResetMessage.class,
            RegistrationRequestMessage.class,
            RegistrationResponseMessage.class,
            FileChatMessage.class,
            PingRequest.class,
            PingResponse.class,
            UserConnectedMessage.class,
            UserDisconnectedMessage.class,
            UserRegisteredMessage.class
    };

    void encode(@NotNull Message<?> message, @NotNull DataOutputStream dataOutputStream) throws IOException;

    @Nullable
    Message<?> decode(@NotNull DataInputStream dataInputStream) throws IOException;
}
