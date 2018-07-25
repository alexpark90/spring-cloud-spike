package com.alexpark;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by alexpark on 25/10/2017.
 */
@EnableBinding(SampleSink.Sink.class)
@EnableSchemaRegistryClient
public class SampleSink {

    // Sink application definition
    @StreamListener(Sink.SAMPLE)
    public void receive(User fooMessage) {
        System.out.println("******************");
        System.out.println("At the Sink");
        System.out.println("******************");
        System.out.println("Received transformed message " + fooMessage.getName() + " of type " + fooMessage.getClass());
    }

//    @ServiceActivator(inputChannel=Sink.INPUT)
//    public void loggerSink(String payload) {
//        System.out.println("Received: " + payload);
//    }

    public interface Sink {
        String SAMPLE = "sample-sink";

        @Input(SAMPLE)
        SubscribableChannel sampleSink();
    }
}
