package com.guidewire.signagecenter.messaging;

import com.guidewire.signagecenter.messaging.payload.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public abstract class MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessagingService.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Sends message payload to destination
     *
     * @param destination
     * @param payload
     */
    public void sendMessage(String destination, Payload payload) {
        simpMessagingTemplate.convertAndSend(destination, payload);
    }
}
