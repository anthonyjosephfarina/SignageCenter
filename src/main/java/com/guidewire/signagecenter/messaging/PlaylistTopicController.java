package com.guidewire.signagecenter.messaging;

import com.guidewire.signagecenter.messaging.payload.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PlaylistTopicController {

    @SendTo("/topic/playlist-{playlistId}")
    public Payload send(Payload payload) {
        return payload;
    }

}
