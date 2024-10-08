package com.core.payment.processor.common.dto.request.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankRequestDTO {
    @NotBlank(message = "bank name required")
    private String name;
    @NotBlank(message = "bank code required")
    private String code;
    @NotBlank(message = "iconUrl")
    private String iconUrl;
}
