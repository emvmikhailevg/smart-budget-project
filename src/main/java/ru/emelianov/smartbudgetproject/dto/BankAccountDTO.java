package ru.emelianov.smartbudgetproject.dto;

import java.math.BigDecimal;

/**
 * <p>DTO для передачи информации о банковском счете</p>
 *
 * @param id     идентификатор банковского счета
 * @param name   имя банковского счета
 * @param amount сумма на счету
 * @param color  цвет счета в формате Hex
 */
public record BankAccountDTO(
        Long id,
        String name,
        BigDecimal amount,
        String color) { }
