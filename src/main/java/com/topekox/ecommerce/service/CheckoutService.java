package com.topekox.ecommerce.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.topekox.ecommerce.dto.PaymentInfo;
import com.topekox.ecommerce.dto.Purchase;
import com.topekox.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
