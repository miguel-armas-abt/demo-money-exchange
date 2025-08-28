package com.demo.service.entrypoint.exchange.service;

import com.demo.service.entrypoint.exchange.dto.response.MoneyExchangeResponseDto;
import com.demo.service.entrypoint.exchange.repository.exchange.MoneyExchangeRepository;
import com.demo.service.entrypoint.exchange.mapper.MoneyExchangeResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MoneyExchangeServiceImpl implements MoneyExchangeService {

  private final MoneyExchangeRepository repository;
  private final MoneyExchangeResponseMapper mapper;

  @Override
  public Mono<MoneyExchangeResponseDto> getMoneyExchange(Map<String, String> headers,
                                                         String baseCode, String targetCode) {
    return repository.getMoneyExchange(headers, baseCode, targetCode)
        .map(mapper::toDto);
  }
}
