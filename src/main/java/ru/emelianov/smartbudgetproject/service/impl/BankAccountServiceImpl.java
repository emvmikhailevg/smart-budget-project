package ru.emelianov.smartbudgetproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.emelianov.smartbudgetproject.database.model.BankAccount;
import ru.emelianov.smartbudgetproject.database.model.User;
import ru.emelianov.smartbudgetproject.database.repository.BankAccountRepository;
import ru.emelianov.smartbudgetproject.dto.BankAccountDTO;
import ru.emelianov.smartbudgetproject.exception.BankAccountDeletionException;
import ru.emelianov.smartbudgetproject.exception.BankAccountNotFoundException;
import ru.emelianov.smartbudgetproject.exception.InsufficientFundsException;
import ru.emelianov.smartbudgetproject.exception.SameAccountTransferException;
import ru.emelianov.smartbudgetproject.mapper.BankAccountMapper;
import ru.emelianov.smartbudgetproject.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;

import static ru.emelianov.smartbudgetproject.message.bankaccount.BankAccountErrorMessage.*;

/**
 * <p>Реализация сервиса для управления банковскими счетами</p>
 */
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public void add(User user, String name, BigDecimal amount, String color) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setUser(user);
        bankAccount.setName(name);
        bankAccount.addAmount(amount);
        bankAccount.setColor(color);

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccountDTO> findAllByUserId(Long userId) {
        return bankAccountRepository.findByUserId(userId).stream()
                .map(bankAccountMapper::convertToDTO)
                .toList();
    }

    @Override
    public BankAccountDTO findByUserId(Long userId) {
        return bankAccountRepository.findByUserId(userId).stream()
                .map(bankAccountMapper::convertToDTO)
                .findFirst()
                .orElseThrow(() -> new BankAccountNotFoundException(ACCOUNT_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long accountId) {
        try {
            bankAccountRepository.deleteById(accountId);
        } catch (DataIntegrityViolationException ex) {
            throw new BankAccountDeletionException(ACCOUNT_DELETION_FAILED.getMessage());
        }
    }

    @Override
    public void editBankAccount(Long accountId, String name, BigDecimal amount, String color) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException(ACCOUNT_NOT_FOUND.getMessage()));

        bankAccount.setName(name);
        bankAccount.setAmount(amount);
        bankAccount.setColor(color);

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transferAmountBetweenAccounts(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        if (fromAccountId.equals(toAccountId)) {
            throw new SameAccountTransferException(SAME_ACCOUNT_TRANSFER_NOT_ALLOWED.getMessage());
        }

        BankAccount fromAccount = bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(SOURCE_ACCOUNT_NOT_FOUND.getMessage()));

        BankAccount toAccount = bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(TARGET_ACCOUNT_NOT_FOUND.getMessage()));

        if (fromAccount.getAmount().compareTo(amount) < 0) {
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS.getMessage());
        }

        fromAccount.subtractAmount(amount);
        toAccount.addAmount(amount);

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);
    }

}
