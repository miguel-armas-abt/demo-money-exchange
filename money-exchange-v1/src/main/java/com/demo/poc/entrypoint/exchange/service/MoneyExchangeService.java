package com.demo.poc.entrypoint.exchange.service;

import com.demo.poc.entrypoint.exchange.dto.response.MoneyExchangeResponseDto;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MoneyExchangeService {

  Mono<MoneyExchangeResponseDto> getMoneyExchange(Map<String, String> headers,
                                                  String baseCode,
                                                  String targetCode);
}
