package com.topekox.ecommerce.dao;

import com.topekox.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long > {
}
