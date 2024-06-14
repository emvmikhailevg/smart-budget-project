package ru.emelianov.smartbudgetproject.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>Сущность банковского счета в системе</p>
 */
@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    /**
     * Уникальный идентификатор банковского счета
     */
    @Id
    @SequenceGenerator(name = "bank_account_id_seq", sequenceName = "bank_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_id_seq")
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Имя банковского счета
     */
    @Column(nullable = false)
    private String name;

    /**
     * Владелец банковского счета
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Сумма банковского счета
     */
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * Цвет банковского счета в формате Hex
     */
    @Column(nullable = false, length = 7)
    private String color;

    /**
     * Добавляет указанную сумму к текущему балансу счета
     *
     * @param amount сумма для добавления
     */
    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    /**
     * Вычитает указанную сумму из текущего баланса счета
     *
     * @param amount сумма для вычитания
     */
    public void subtractAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    /**
     * Проверяет равенство объектов {@link BankAccount}
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Возвращает хеш-код объекта {@link BankAccount}
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
