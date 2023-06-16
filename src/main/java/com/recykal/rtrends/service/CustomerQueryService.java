package com.recykal.rtrends.service;

import com.recykal.rtrends.domain.*; // for static metamodels
import com.recykal.rtrends.domain.Customer;
import com.recykal.rtrends.repository.CustomerRepository;
import com.recykal.rtrends.service.criteria.CustomerCriteria;
import com.recykal.rtrends.service.dto.CustomerDTO;
import com.recykal.rtrends.service.mapper.CustomerMapper;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Customer} entities in the database.
 * The main input is a {@link CustomerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerDTO} or a {@link Page} of {@link CustomerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerQueryService extends QueryService<Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerQueryService.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerQueryService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Return a {@link List} of {@link CustomerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerDTO> findByCriteria(CustomerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerMapper.toDto(customerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findByCriteria(CustomerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.findAll(specification, page).map(customerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Customer> createSpecification(CustomerCriteria criteria) {
        Specification<Customer> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Customer_.id));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), Customer_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), Customer_.lastName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Customer_.email));
            }
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), Customer_.username));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), Customer_.password));
            }
            if (criteria.getMobile() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMobile(), Customer_.mobile));
            }
            if (criteria.getAddressId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getAddressId(), root -> root.join(Customer_.addresses, JoinType.LEFT).get(Address_.id))
                    );
            }
            if (criteria.getOrdersId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getOrdersId(), root -> root.join(Customer_.orders, JoinType.LEFT).get(Orders_.id))
                    );
            }
            if (criteria.getCartId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCartId(), root -> root.join(Customer_.cart, JoinType.LEFT).get(Cart_.id))
                    );
            }
        }
        return specification;
    }
}
