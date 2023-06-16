package com.recykal.rtrends.service.mapper;

import com.recykal.rtrends.domain.Cart;
import com.recykal.rtrends.domain.OrderItem;
import com.recykal.rtrends.domain.Product;
import com.recykal.rtrends.service.dto.CartDTO;
import com.recykal.rtrends.service.dto.OrderItemDTO;
import com.recykal.rtrends.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "carts", source = "carts", qualifiedByName = "cartIdSet")
    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "orderItemIdSet")
    ProductDTO toDto(Product s);

    @Mapping(target = "removeCart", ignore = true)
    @Mapping(target = "removeOrderItem", ignore = true)
    Product toEntity(ProductDTO productDTO);

    @Named("cartId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CartDTO toDtoCartId(Cart cart);

    @Named("cartIdSet")
    default Set<CartDTO> toDtoCartIdSet(Set<Cart> cart) {
        return cart.stream().map(this::toDtoCartId).collect(Collectors.toSet());
    }

    @Named("orderItemId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderItemDTO toDtoOrderItemId(OrderItem orderItem);

    @Named("orderItemIdSet")
    default Set<OrderItemDTO> toDtoOrderItemIdSet(Set<OrderItem> orderItem) {
        return orderItem.stream().map(this::toDtoOrderItemId).collect(Collectors.toSet());
    }
}
