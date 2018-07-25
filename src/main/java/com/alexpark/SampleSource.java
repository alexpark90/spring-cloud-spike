package com.alexpark;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by alexpark on 25/10/2017.
 */
@EnableBinding(SampleSource.Source.class)
public class SampleSource {

    @Bean
    @InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return new MessageSource<String>() {
            public Message<String> receive() {
                System.out.println("******************");
                System.out.println("At the Source");
                System.out.println("******************");
                String value = "{\"value\":\"Hello World!\"}";
                System.out.println("Sending value: " + value);
                return MessageBuilder.withPayload(value).setHeader(MessageHeaders.CONTENT_TYPE, "application/json").build();
            }
        };
    }

    public interface Source {
        String SAMPLE = "sample-source";

        @Output(SAMPLE)
        MessageChannel sampleSource();
    }
}