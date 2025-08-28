package com.demo.service.entrypoint.exchange.repository.exchange.error;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.demo.commons.restclient.error.RestClientErrorExtractor;
import com.demo.commons.restclient.error.RestClientErrorMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoneyExchangeErrorExtractor implements RestClientErrorExtractor {

  private final RestClientErrorMapper mapper;

  @Override
  public Optional<Map.Entry<String, String>> getCodeAndMessage(String jsonBody) {
    return mapper.getCodeAndMessage(jsonBody, MoneyExchangeError.class, errorMapper);
  }

  private final Function<MoneyExchangeError, Map.Entry<String, String>> errorMapper = errorDetail
      -> Map.of(errorDetail.getResult(), errorDetail.getErrorType()).entrySet().iterator().next();

  @Override
  public boolean supports(Class<?> wrapperClass) {
    return wrapperClass.isAssignableFrom(MoneyExchangeError.class);
  }

}
