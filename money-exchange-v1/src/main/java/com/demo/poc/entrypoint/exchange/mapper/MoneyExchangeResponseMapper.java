package com.demo.poc.entrypoint.exchange.mapper;

import com.demo.poc.entrypoint.exchange.dto.response.MoneyExchangeResponseDto;
import com.demo.poc.entrypoint.exchange.repository.exchange.wrapper.response.MoneyExchangeResponseWrapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyExchangeResponseMapper {

  MoneyExchangeResponseDto toDto(MoneyExchangeResponseWrapper response);
}
