package com.alexpark;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by alexpark on 25/10/2017.
 */

@EnableBinding(Processor.class)
public class SampleTransformer {

    private static final String TRANSFORMATION_VALUE = "APPENDING... ";

    // Transformer application definition

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public BeforeFoo receive(BeforeFoo barMessage) {
        System.out.println("******************");
        System.out.println("At the transformer");
        System.out.println("******************");
        System.out.println("Received value "+ barMessage.getValue() + " of type " + barMessage.getClass());
        System.out.println("Transforming the value to " + TRANSFORMATION_VALUE + " and with the type " + barMessage.getClass());
        barMessage.setValue(TRANSFORMATION_VALUE);
        return barMessage;
    }
}