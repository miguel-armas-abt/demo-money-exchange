package com.demo.poc.entrypoint.exchange.rest;

import com.demo.poc.commons.constants.RestConstants;

import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MoneyExchangeRestService {

  @Bean("money-exchange")
  public RouterFunction<ServerResponse> build(MoneyExchangeHandler moneyExchangeHandler) {
    return nest(
        path(RestConstants.BASE_URI),
        route()
            .GET("/conversion-rate", accept(APPLICATION_NDJSON), moneyExchangeHandler::generateReport)
            .build()
    );
  }
}