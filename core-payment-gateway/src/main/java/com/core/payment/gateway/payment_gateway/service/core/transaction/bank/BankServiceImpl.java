package com.core.payment.gateway.payment_gateway.service.core.transaction.bank;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateBankRequestDTO;
import com.core.payment.gateway.payment_gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.payment_gateway.entity.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    @Override
    public GenericApiResponse<Bank> addBank(CreateBankRequestDTO request) {
        return null;
    }

    @Override
    public GenericApiResponse<Bank> getAll(CreateBankRequestDTO request) {
        return null;
    }

    @Override
    public GenericApiResponse<Bank> delete(CreateBankRequestDTO request) {
        return null;
    }

    @Override
    public GenericApiResponse<Bank> update(CreateBankRequestDTO request) {
        return null;
    }
}
