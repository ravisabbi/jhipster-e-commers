package com.recykal.rtrends.service.mapper;

import com.recykal.rtrends.domain.Cart;
import com.recykal.rtrends.domain.Customer;
import com.recykal.rtrends.service.dto.CartDTO;
import com.recykal.rtrends.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    CartDTO toDto(Cart s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
