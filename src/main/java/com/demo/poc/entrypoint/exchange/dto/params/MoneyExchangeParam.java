package com.demo.poc.entrypoint.exchange.dto.params;

import com.demo.poc.commons.core.validations.params.DefaultParams;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoneyExchangeParam extends DefaultParams {

  @NotEmpty
  private String baseCode;

  @NotEmpty
  private String targetCode;
}
