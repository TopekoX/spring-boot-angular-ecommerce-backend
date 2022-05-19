package com.topekox.ecommerce.dto;

import com.topekox.ecommerce.entity.Address;
import com.topekox.ecommerce.entity.Customer;
import com.topekox.ecommerce.entity.Order;
import com.topekox.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem > orderItems;

}
