package com.cms4j.springgraphql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class ChatRoomController {
   @Autowired
   ChatRoomService service;

    @MutationMapping("createMessage")
    public Mono<Message> createMessage(@Argument MessageInput message) {
        log.info("createMessage({})", message);
        return service.createMessage(message);
    }

    @SubscriptionMapping("listen")
    public Flux<Message> listen() {
        log.info("listen()");
        return service.listen();
    }

    @QueryMapping("messages")
    public Flux<Message> getMessages() {
        log.info("getMessages()");
        return service.getMessages();
    }
}
