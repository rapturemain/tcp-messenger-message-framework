package org.rapturemain.tcpmessengermessageframework.message;

import org.rapturemain.tcpmessengermessageframework.message.messages.ConnectionResetMessage;
import org.rapturemain.tcpmessengermessageframework.message.messages.FileChatMessage;
import org.rapturemain.tcpmessengermessageframework.message.messages.Message;
import org.rapturemain.tcpmessengermessageframework.message.messages.SimpleChatMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rapturemain.tcpmessengermessageframework.message.messages.request.PingRequest;
import org.rapturemain.tcpmessengermessageframework.message.messages.request.RegistrationRequestMessage;
import org.rapturemain.tcpmessengermessageframework.message.messages.response.PingResponse;
import org.rapturemain.tcpmessengermessageframework.message.messages.response.RegistrationResponseMessage;

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
            PingResponse.class
    };

    void encode(@NotNull Message<?> message, @NotNull DataOutputStream dataOutputStream) throws IOException;

    @Nullable
    Message<?> decode(@NotNull DataInputStream dataInputStream) throws IOException;
}
