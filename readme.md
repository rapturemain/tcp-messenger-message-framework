# TCP Messenger Message Framework

TCP Messenger Message Framework is a message container and encoder/decoder for the messages.

### Protocol

Java's `DataInputStream` and `DataOutputStream` is used to save and load messages. 
Any `MessageBaseEntry` uses this classes to encode/decode it contexts. 
So any base entry must implement `read` and `write` method.

Any actual message implements `Message` class, encoding and decoding already implemented, 
you need only specify `getEntryId()` method, that encodes message class. 
If you need you can also override `write` and `read` methods for custom serialization.

Example of a message and encoding. 
To see more, check javadoc of `MessageBaseEntry` implementations and `Message` interface.
```java
public class SimpleChatMessage implements ChatMessage<SimpleChatMessage> {
    private static final int ENTRY_ID = 0;

    private TimestampEntry timestamp;
    private StringEntry senderName;
    private StringEntry text;

    @Override
    public int getEntryId() {
        return ENTRY_ID;
    }
}
```

```
| { byte count } | - { purpose }

| byte4x | - entry id (message identifier)

|  byte  | - is next item null
| byte8x | - TimestampEntry (if not null)

|  byte  | - is next item null
| byte2x | - StringEntry (if not null)
| byte[] | 

|  byte  | - is next item null
| byte2x | - StringEntry (if not null)
| byte[] | 
```