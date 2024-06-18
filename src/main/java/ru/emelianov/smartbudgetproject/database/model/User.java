package ru.emelianov.smartbudgetproject.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * <p>Сущность пользователя в системе</p>
 */
@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /**
     * Уникальный идентификатор пользователя
     */
    @Id
    @SequenceGenerator(name = "app_user_id_seq", sequenceName = "app_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_id_seq")
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Электронная почта пользователя. Должна быть уникальной и не пустой
     */
    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;

    /**
     * Имя пользователя. Должна быть уникальной и не пустой
     */
    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;

    /**
     * Пароль пользователя. Не должен быть пустым
     */
    @Column(nullable = false)
    @NotBlank
    private String password;

    /**
     * Роль пользователя в системе. По умолчанию {@link Role#USER}
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    /**
     * Набор банковских счетов, связанных с пользователем
     */
    @OneToMany(mappedBy = "user")
    private Set<BankAccount> bankAccounts = new HashSet<>();

    /**
     * Проверяет равенство объектов {@link User}
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        if (id == null && that.id == null) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    /**
     * Возвращает хеш-код объекта {@link User}
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Возвращает коллекцию ролей пользователя
     *
     * @return коллекция ролей пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Проверяет, не истек ли срок действия учетной записи
     *
     * @return true, если учетная запись активна, иначе false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирована ли учетная запись
     *
     * @return true, если учетная запись не заблокирована, иначе false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истек ли срок действия учетных данных
     *
     * @return true, если учетные данные активны, иначе false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, включена ли учетная запись
     *
     * @return true, если учетная запись включена, иначе false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
