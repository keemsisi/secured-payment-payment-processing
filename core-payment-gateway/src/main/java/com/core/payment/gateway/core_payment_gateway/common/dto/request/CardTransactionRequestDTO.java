package com.core.payment.gateway.core_payment_gateway.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionRequestDTO {
    private double amount;
    private String number;
    private String cvv;
    private String expiry;
}
