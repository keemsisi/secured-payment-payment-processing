package com.core.payment.gateway.service.core.transaction.wallet;

import com.core.payment.gateway.common.dto.request.wallet.WalletTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.TransactionChannel;
import com.core.payment.gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.common.enums.TransactionStatus;
import com.core.payment.gateway.common.exceptions.ApplicationException;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.entity.Wallet;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.core.payment.gateway.service.core.transaction.TransactionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final TransactionServiceImpl transactionService;
    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    @Override
    public Transaction getById(Long transactionId) throws JsonProcessingException {
        return transactionService.getGetWalletTransactionById(transactionId);
    }

    @Override
    public Transaction initTransfer(final WalletTransactionRequestDTO request) throws JsonProcessingException {
        final var senderWallet = walletService.getWalletById(request.getSenderWalletId());
        final var receiverWallet = walletService.getWalletById(request.getReceiverWalletId());
        if (senderWallet.getBalanceAfter().compareTo(BigDecimal.ZERO) <= 0) {
            final var responseCode = ResponseCodeMapping.WALLET_TRANSACTION_INSUFFICIENT_BALANCE;
            throw new ApplicationException(400, responseCode.getCode(), responseCode.getMessage());
        }
        final var transaction = create(request, senderWallet, receiverWallet);
        walletService.debitWallet(transaction, senderWallet);
        transaction.setDateCompleted(LocalDateTime.now());
        transaction.setDateUpdated(LocalDateTime.now());
        save(transaction);
        return transaction;
    }

    @Override
    public Transaction create(final WalletTransactionRequestDTO request, final Wallet senderWallet, final Wallet receiverWallet) throws JsonProcessingException {
        final var meta = objectMapper.writeValueAsString(request);
        final var transaction = Transaction.builder()
                .amount(request.getAmount())
                .senderAccountId(senderWallet.getAccountNumber())
                .senderName(senderWallet.getAccountName())
                .receiverAccountId(receiverWallet.getAccountNumber())
                .receiverName(receiverWallet.getAccountName())
                .fee(BigDecimal.TEN)
                .channel(TransactionChannel.WALLET)
                .dateCompleted(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .metaData(meta).build();
        transaction.setDateCreated(LocalDateTime.now());
        return transactionService.create(transaction);
    }

    @Override
    public Transaction save(final Transaction transaction) {
        return transactionService.save(transaction);
    }
}
