package com.demo.poc.entrypoint.exchange.repository.exchange.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyExchangeError implements Serializable {

  private String result;
  private String documentation;

  @JsonProperty("terms-of-use")
  private String termsOfUse;

  @JsonProperty("error-type")
  private String errorType;
}
