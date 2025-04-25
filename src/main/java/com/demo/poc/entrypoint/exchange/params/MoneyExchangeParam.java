package com.demo.poc.entrypoint.exchange.params;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MoneyExchangeParam {

  @NotEmpty
  private String baseCode;

  @NotEmpty
  private String targetCode;
}
