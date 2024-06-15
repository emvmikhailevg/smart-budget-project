package ru.emelianov.smartbudgetproject.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Сущность категории транзакций</p>
 */
@Entity
@Table(name = "transaction_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    /**
     * Уникальный идентификатор категории
     */
    @Id
    @SequenceGenerator(name = "transaction_category_id_seq", sequenceName = "transaction_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_category_id_seq")
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Имя категории
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Цвет категории в формате Hex
     */
    @Column(nullable = false, length = 7)
    private String color;

    /**
     * Тип категории
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;

    /**
     * Транзакции, связанные с данной категорией
     */
    @OneToMany(mappedBy = "category")
    private Set<Transaction> transaction = new HashSet<>();

    /**
     * Проверяет равенство объектов {@link Category}
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Возвращает хеш-код объекта {@link Category}
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
