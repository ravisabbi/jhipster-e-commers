package com.recykal.rtrends.service.mapper;

import com.recykal.rtrends.domain.OrderItem;
import com.recykal.rtrends.domain.Orders;
import com.recykal.rtrends.service.dto.OrderItemDTO;
import com.recykal.rtrends.service.dto.OrdersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderItem} and its DTO {@link OrderItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {
    @Mapping(target = "orders", source = "orders", qualifiedByName = "ordersId")
    OrderItemDTO toDto(OrderItem s);

    @Named("ordersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrdersDTO toDtoOrdersId(Orders orders);
}
