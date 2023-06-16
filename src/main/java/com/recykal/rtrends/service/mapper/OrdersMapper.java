package com.recykal.rtrends.service.mapper;

import com.recykal.rtrends.domain.Customer;
import com.recykal.rtrends.domain.Orders;
import com.recykal.rtrends.service.dto.CustomerDTO;
import com.recykal.rtrends.service.dto.OrdersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Orders} and its DTO {@link OrdersDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrdersMapper extends EntityMapper<OrdersDTO, Orders> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    OrdersDTO toDto(Orders s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
