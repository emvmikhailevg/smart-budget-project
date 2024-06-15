package ru.emelianov.smartbudgetproject.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>Сущность транзакции</p>
 */
@Entity
@Table(name = "app_transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    /**
     * Уникальный идентификатор транзакции
     */
    @Id
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Банковский счет, связанный с транзакцией
     */
    @ManyToOne
    @JoinColumn(name = "bank_account_transaction_id", nullable = false)
    private BankAccount bankAccount;

    /**
     * Категория транзакции
     */
    @ManyToOne
    @JoinColumn(name = "transaction_category_id")
    private Category category;

    /**
     * Сумма транзакции
     */
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * Дата и время транзакции
     */
    @Column(nullable = false)
    private LocalDateTime date;

    /**
     * Проверяет равенство объектов {@link Transaction}
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Возвращает хеш-код объекта {@link Transaction}
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
