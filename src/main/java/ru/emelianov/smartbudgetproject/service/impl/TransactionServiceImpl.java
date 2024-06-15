package ru.emelianov.smartbudgetproject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.emelianov.smartbudgetproject.database.model.BankAccount;
import ru.emelianov.smartbudgetproject.database.model.Transaction;
import ru.emelianov.smartbudgetproject.database.repository.BankAccountRepository;
import ru.emelianov.smartbudgetproject.database.repository.CategoryRepository;
import ru.emelianov.smartbudgetproject.database.repository.TransactionRepository;
import ru.emelianov.smartbudgetproject.dto.TransactionDTO;
import ru.emelianov.smartbudgetproject.exception.TransactionNotFoundException;
import ru.emelianov.smartbudgetproject.mapper.TransactionMapper;
import ru.emelianov.smartbudgetproject.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.emelianov.smartbudgetproject.message.transaction.TransactionErrorMessage.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public void accrual(Long bankAccountId, Long categoryId, BigDecimal amount, LocalDateTime date) {
        createTransaction(bankAccountId, categoryId, amount.abs(), date);
    }

    @Override
    @Transactional
    public void withdraw(Long bankAccountId, Long categoryId, BigDecimal amount, LocalDateTime date) {
        createTransaction(bankAccountId, categoryId, amount.abs().negate(), date);
    }

    @Override
    public void editTransaction(Long transactionId, Long newCategoryId, BigDecimal newAmount, LocalDateTime newDate) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            Transaction currentTransaction = transaction.get();
            currentTransaction.setAmount(newAmount);
            currentTransaction.setCategory(categoryRepository.getReferenceById(newCategoryId));
            currentTransaction.setDate(newDate);
            transactionRepository.save(currentTransaction);
        } else {
            throw new TransactionNotFoundException(TRANSACTION_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void deleteTransactionById(Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            Transaction currentTransaction = transaction.get();
            BankAccount bankAccount = currentTransaction.getBankAccount();
            bankAccount.subtractAmount(currentTransaction.getAmount());
            bankAccountRepository.save(bankAccount);
            transactionRepository.delete(currentTransaction);
        }
    }

    @Override
    public Map<LocalDate, List<TransactionDTO>> findAndGroupByDate(Long bankAccountId) {
        LocalDateTime earliestDate = transactionRepository.findEarliestTransactionDate(bankAccountId).orElse(LocalDateTime.now());
        LocalDateTime latestDate = transactionRepository.findLatestTransactionDate(bankAccountId).orElse(LocalDateTime.now());
        return findAndGroupByDate(bankAccountId, earliestDate, latestDate);
    }

    @Override
    public Map<LocalDate, List<TransactionDTO>> findAndGroupByDate(
            Long bankAccountId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions =
                transactionRepository.findByBankAccountIdAndDateBetween(bankAccountId, startDate, endDate);

        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getDate().toLocalDate(),
                        LinkedHashMap::new,
                        Collectors.mapping(transactionMapper::convertToDTO, Collectors.toList())
                ));
    }

    @Override
    public List<TransactionDTO> findBetweenDates(Long bankAccountId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions =
                transactionRepository.findByBankAccountIdAndDateBetween(bankAccountId, startDate, endDate);
        return transactions.stream().map(transactionMapper::convertToDTO).toList();
    }

    @Override
    public List<TransactionDTO> findBetweenDates(Long bankAccountId) {
        LocalDateTime earliestDate =
                transactionRepository.findEarliestTransactionDate(bankAccountId).orElse(LocalDateTime.now());
        LocalDateTime latestDate =
                transactionRepository.findLatestTransactionDate(bankAccountId).orElse(LocalDateTime.now());
        return findBetweenDates(bankAccountId, earliestDate, latestDate);
    }

    /**
     * Создает и сохраняет транзакцию для указанного банковского счета
     *
     * @param bankAccountId ID банковского счета
     * @param categoryId    ID категории
     * @param amount        сумма транзакции
     * @param date          дата транзакции
     */
    private void createTransaction(Long bankAccountId, Long categoryId, BigDecimal amount, LocalDateTime date) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);
        if (bankAccountOptional.isPresent()) {
            BankAccount bankAccount = bankAccountOptional.get();
            Transaction transaction = new Transaction();
            transaction.setBankAccount(bankAccount);
            transaction.setCategory(categoryRepository.getReferenceById(categoryId));
            transaction.setAmount(amount);
            transaction.setDate(date);
            transactionRepository.save(transaction);
            bankAccount.addAmount(amount);
            bankAccountRepository.save(bankAccount);
        }
    }

}
