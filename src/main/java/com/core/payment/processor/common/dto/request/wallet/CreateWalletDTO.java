package com.core.payment.processor.common.dto.request.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWalletDTO {
    private String name;
    private Long ownerId;
}
