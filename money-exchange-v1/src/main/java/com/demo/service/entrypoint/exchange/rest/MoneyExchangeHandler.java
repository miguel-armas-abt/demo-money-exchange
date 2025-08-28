package com.demo.service.entrypoint.exchange.rest;

import com.demo.commons.restserver.utils.RestServerUtils;
import com.demo.commons.validations.headers.DefaultHeaders;
import com.demo.commons.validations.ParamValidator;

import java.util.Map;

import com.demo.service.entrypoint.exchange.params.MoneyExchangeParam;
import com.demo.service.entrypoint.exchange.service.MoneyExchangeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MoneyExchangeHandler {

  private final ParamValidator paramValidator;
  private final MoneyExchangeService moneyExchangeService;

  public Mono<ServerResponse> generateReport(ServerRequest serverRequest) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(serverRequest);


    Mono<MoneyExchangeParam> params = paramValidator.validateAndGet(RestServerUtils.extractQueryParamsAsMap(serverRequest), MoneyExchangeParam.class);

    return paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .zipWith(params)
        .flatMap(tuple -> moneyExchangeService.getMoneyExchange(headers, tuple.getT2().getBaseCode(), tuple.getT2().getTargetCode()))
        .flatMap(response -> ServerResponse.ok()
            .headers(httpHeaders -> RestServerUtils.buildResponseHeaders(serverRequest.headers()).accept(httpHeaders))
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(response)));
  }
}
