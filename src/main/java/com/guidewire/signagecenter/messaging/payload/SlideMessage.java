package com.guidewire.signagecenter.messaging.payload;

import com.guidewire.signagecenter.messaging.MessageType;

public class SlideMessage implements Payload {

    private Long id;

    private MessageType messageType;

    public SlideMessage() {

    }

    public SlideMessage(Long id, MessageType messageType) {
        this.id = id;
        this.messageType = messageType;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
