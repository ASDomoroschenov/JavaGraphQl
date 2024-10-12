package ru.sberp.springgraphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQlConfig {

  @Bean
  public GraphQlClient graphQlClient() {
    return HttpGraphQlClient.builder(
        WebClient.builder()
            .baseUrl("http://localhost:8080/graphql")
            .build()
    ).build();
  }

}
