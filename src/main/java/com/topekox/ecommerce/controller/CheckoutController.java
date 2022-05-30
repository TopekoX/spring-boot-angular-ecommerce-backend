package com.topekox.ecommerce.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.topekox.ecommerce.dto.PaymentInfo;
import com.topekox.ecommerce.dto.Purchase;
import com.topekox.ecommerce.dto.PurchaseResponse;
import com.topekox.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	private Logger logger = Logger.getLogger(getClass().getName());
	
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {

        logger.info("payment info amount : " + paymentInfo.getAmount());

        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

}
