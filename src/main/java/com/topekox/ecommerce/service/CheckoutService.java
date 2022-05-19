package com.topekox.ecommerce.service;

import com.topekox.ecommerce.dto.Purchase;
import com.topekox.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
