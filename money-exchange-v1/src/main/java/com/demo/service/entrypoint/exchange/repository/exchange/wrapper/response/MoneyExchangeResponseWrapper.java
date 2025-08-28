package com.demo.service.entrypoint.exchange.repository.exchange.wrapper.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyExchangeResponseWrapper implements Serializable {

  private String result;
  private String documentation;

  @JsonProperty("terms_of_use")
  private String termsOfUse;

  @JsonProperty("time_last_update_unix")
  private String timeLastUpdateUnix;

  @JsonProperty("time_last_update_utc")
  private String timeLastUpdateUtc;

  @JsonProperty("time_next_update_unix")
  private String timeNextUpdateUnix;

  @JsonProperty("time_next_update_utc")
  private String timeNextUpdateUtc;

  @JsonProperty("base_code")
  private String baseCode;

  @JsonProperty("target_code")
  private String targetCode;

  @JsonProperty("conversion_rate")
  private String conversionRate;
}
