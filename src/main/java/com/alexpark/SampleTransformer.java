package com.alexpark;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by alexpark on 25/10/2017.
 */

@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
public class SampleTransformer {

    private static final String TRANSFORMATION_VALUE = "ALEX Park";

    // Transformer application definition

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Message<User> receive(BeforeFoo barMessage) {
        System.out.println("******************");
        System.out.println("At the transformer");
        System.out.println("******************");
        System.out.println("Received value "+ barMessage.getValue() + " of type " + barMessage.getClass());
        System.out.println("Transforming the value to " + TRANSFORMATION_VALUE + " and with the type " + barMessage.getClass());
//        Foo fooMessage = new Foo();
        User fooMessage = new User();
        fooMessage.setName(TRANSFORMATION_VALUE);
        return MessageBuilder.withPayload(fooMessage).build();
    }
}