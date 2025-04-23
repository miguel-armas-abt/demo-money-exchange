package com.demo.poc.entrypoint.exchange.rest;

import com.demo.poc.commons.core.restclient.utils.QueryParamFiller;
import com.demo.poc.commons.core.restserver.ServerResponseBuilder;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.headers.HeaderValidator;
import com.demo.poc.commons.core.validations.params.ParamValidator;

import java.util.Map;

import com.demo.poc.entrypoint.exchange.dto.params.MoneyExchangeParam;
import com.demo.poc.entrypoint.exchange.service.MoneyExchangeService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.extractHeadersAsMap;

@Component
@RequiredArgsConstructor
public class MoneyExchangeHandler {

  private final HeaderValidator headerValidator;
  private final ParamValidator paramValidator;
  private final MoneyExchangeService moneyExchangeService;

  public Mono<ServerResponse> generateReport(ServerRequest serverRequest) {
    Map<String, String> headers = extractHeadersAsMap(serverRequest);
    headerValidator.validate(headers, DefaultHeaders.class);

    MoneyExchangeParam params = paramValidator.validateAndRetrieve(QueryParamFiller.extractQueryParamsAsMap(serverRequest), MoneyExchangeParam.class);

    return moneyExchangeService.getMoneyExchange(headers, params.getBaseCode(), params.getTargetCode())
        .flatMap(response -> ServerResponseBuilder.buildMono(ServerResponse.ok(), serverRequest.headers(), response));
  }
}
