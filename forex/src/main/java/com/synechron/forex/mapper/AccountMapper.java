package com.synechron.forex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.synechron.forex.dto.AccountDto;
import com.synechron.forex.models.Account;

@Mapper
public interface AccountMapper {

	Account INSTANCE = Mappers.getMapper(Account.class);

	AccountDto AccountToAccountDto(Account account);

}
