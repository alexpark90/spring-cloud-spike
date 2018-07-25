package com.alexpark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchemaRegistryConfig {

    @Bean
    public SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.stream.schemaRegistryClient.endpoint}") String endpoint){
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);

        System.out.println("================================================");
        System.out.println("Created schema registry!!!!!!!!");

        return client;
    }
}
