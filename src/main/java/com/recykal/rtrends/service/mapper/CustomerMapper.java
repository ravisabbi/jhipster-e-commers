package com.recykal.rtrends.service.mapper;

import com.recykal.rtrends.domain.Customer;
import com.recykal.rtrends.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {}
