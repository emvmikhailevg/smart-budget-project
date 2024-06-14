package ru.emelianov.smartbudgetproject.dto;

/**
 * <p>DTO для передачи информации о банковском счете</p>
 *
 * @param id     идентификатор банковского счета
 * @param name   имя банковского счета
 * @param amount сумма на счете в строковом формате
 * @param color  цвет счета в формате Hex
 */
public record BankAccountDTO(
        Long id,
        String name,
        String amount,
        String color) { }
