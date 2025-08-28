package com.demo.service.entrypoint.exchange.mapper;

import com.demo.service.entrypoint.exchange.dto.response.MoneyExchangeResponseDto;
import com.demo.service.entrypoint.exchange.repository.exchange.wrapper.response.MoneyExchangeResponseWrapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyExchangeResponseMapper {

  MoneyExchangeResponseDto toDto(MoneyExchangeResponseWrapper response);
}
