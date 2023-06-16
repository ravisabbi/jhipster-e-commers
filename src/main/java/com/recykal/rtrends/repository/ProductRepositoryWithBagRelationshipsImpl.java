package com.recykal.rtrends.repository;

import com.recykal.rtrends.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ProductRepositoryWithBagRelationshipsImpl implements ProductRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> fetchBagRelationships(Optional<Product> product) {
        return product.map(this::fetchCarts).map(this::fetchOrderItems);
    }

    @Override
    public Page<Product> fetchBagRelationships(Page<Product> products) {
        return new PageImpl<>(fetchBagRelationships(products.getContent()), products.getPageable(), products.getTotalElements());
    }

    @Override
    public List<Product> fetchBagRelationships(List<Product> products) {
        return Optional.of(products).map(this::fetchCarts).map(this::fetchOrderItems).orElse(Collections.emptyList());
    }

    Product fetchCarts(Product result) {
        return entityManager
            .createQuery("select product from Product product left join fetch product.carts where product.id = :id", Product.class)
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<Product> fetchCarts(List<Product> products) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, products.size()).forEach(index -> order.put(products.get(index).getId(), index));
        List<Product> result = entityManager
            .createQuery("select product from Product product left join fetch product.carts where product in :products", Product.class)
            .setParameter("products", products)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Product fetchOrderItems(Product result) {
        return entityManager
            .createQuery("select product from Product product left join fetch product.orderItems where product.id = :id", Product.class)
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<Product> fetchOrderItems(List<Product> products) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, products.size()).forEach(index -> order.put(products.get(index).getId(), index));
        List<Product> result = entityManager
            .createQuery("select product from Product product left join fetch product.orderItems where product in :products", Product.class)
            .setParameter("products", products)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
