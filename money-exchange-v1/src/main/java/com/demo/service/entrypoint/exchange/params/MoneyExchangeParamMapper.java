package com.demo.service.entrypoint.exchange.params;

import java.util.HashMap;
import java.util.Map;

import com.demo.commons.validations.ParamMapper;

import org.springframework.stereotype.Component;

@Component
public class MoneyExchangeParamMapper implements ParamMapper<MoneyExchangeParam> {

  private static final String BASE_CODE = "baseCode";
  private static final String TARGET_CODE = "targetCode";

  @Override
  public Map.Entry<MoneyExchangeParam, Map<String, String>> map(Map<String, String> params) {
    MoneyExchangeParam param = MoneyExchangeParam.builder()
        .baseCode(params.get(BASE_CODE))
        .targetCode(params.get(TARGET_CODE))
        .build();

    Map<String, String> paramMap = new HashMap<>();
    paramMap.put(BASE_CODE, param.getBaseCode());
    paramMap.put(TARGET_CODE, param.getTargetCode());

    return Map.entry(param, paramMap);
  }

  @Override
  public boolean supports(Class<?> paramClass) {
    return MoneyExchangeParam.class.isAssignableFrom(paramClass);
  }
}
