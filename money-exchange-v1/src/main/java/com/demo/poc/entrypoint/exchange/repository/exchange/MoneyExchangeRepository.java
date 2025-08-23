package com.demo.poc.entrypoint.exchange.repository.exchange;

import java.util.Map;

import com.demo.poc.commons.core.properties.restclient.RestClient;
import com.demo.poc.commons.core.restclient.WebClientFactory;
import com.demo.poc.commons.core.restclient.error.RestClientErrorHandler;
import com.demo.poc.commons.properties.ApplicationProperties;
import com.demo.poc.entrypoint.exchange.repository.exchange.error.MoneyExchangeError;
import com.demo.poc.entrypoint.exchange.repository.exchange.wrapper.response.MoneyExchangeResponseWrapper;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.fillHeaders;

@Repository
public class MoneyExchangeRepository {

  private static final String SERVICE_NAME = "money-exchange";

  private final RestClientErrorHandler errorHandler;
  private final WebClient webClient;
  private final RestClient restClient;

  public MoneyExchangeRepository(ApplicationProperties properties,
                              RestClientErrorHandler errorHandler,
                              WebClientFactory webClientFactory) {
    this.errorHandler = errorHandler;

    this.restClient = properties.searchRestClient(SERVICE_NAME);
    this.webClient = webClientFactory.createWebClient(restClient.getPerformance(), SERVICE_NAME);
  }

  public Mono<MoneyExchangeResponseWrapper> getMoneyExchange(Map<String, String> headers,
                                                             String baseCode,
                                                             String targetCode) {
    return webClient.get()
        .uri(this.restClient.getRequest().getEndpoint() + "/{baseCode}/{targetCode}", baseCode, targetCode)
        .headers(x -> fillHeaders(this.restClient.getRequest().getHeaders(), headers).accept(x))
        .retrieve()
        .onStatus(HttpStatusCode::isError, this::handleError)
        .toEntity(MoneyExchangeResponseWrapper.class)
        .mapNotNull(HttpEntity::getBody);
  }

  private Mono<? extends Throwable> handleError(ClientResponse clientResponse) {
    return errorHandler.handleError(clientResponse, MoneyExchangeError.class, SERVICE_NAME);
  }

}
