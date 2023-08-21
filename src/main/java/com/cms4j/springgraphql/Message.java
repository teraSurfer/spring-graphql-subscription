package com.cms4j.springgraphql;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Builder
public record Message(@Id ObjectId id, String content) {

}
