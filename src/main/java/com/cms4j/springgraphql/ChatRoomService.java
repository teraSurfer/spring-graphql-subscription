package com.cms4j.springgraphql;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
@Service
public class ChatRoomService {

    @Autowired
    MessageRepository cappedMessageRepository;

    @Autowired
    ReactiveMongoOperations mongoOperations;

    public Mono<Message> createMessage(MessageInput input) {
        Message message = Message.builder()
                .content(input.content())
                .build();
        return cappedMessageRepository.save(message);
    }

    public Flux<Message> getMessages() {
        return cappedMessageRepository.findAll();
    }

    public Flux<Message> listen() {
        return mongoOperations.tail(new BasicQuery("{}"), Message.class).doOnError(throwable -> {
            throwable.printStackTrace();
            log.error("error - {}", throwable.getMessage());
        });
    }

}
