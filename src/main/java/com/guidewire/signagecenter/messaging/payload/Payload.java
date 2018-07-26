package com.guidewire.signagecenter.messaging.payload;

import com.guidewire.signagecenter.messaging.MessageType;

public interface Payload {

    void setId(Long id);

    Long getId();

    void setMessageType(MessageType messageType);

    MessageType getMessageType();
}
