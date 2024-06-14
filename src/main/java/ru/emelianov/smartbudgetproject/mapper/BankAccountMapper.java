package ru.emelianov.smartbudgetproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.emelianov.smartbudgetproject.database.model.BankAccount;
import ru.emelianov.smartbudgetproject.dto.BankAccountDTO;

/**
 * <p>Маппер для преобразования объектов {@link BankAccount} в объекты {@link BankAccountDTO}</p>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {

    /**
     * Преобразует объект {@link BankAccount} в объект {@link BankAccountDTO}
     *
     * @param bankAccount объект банковского счета
     * @return объект DTO банковского счета
     */
    BankAccountDTO convertToDTO(BankAccount bankAccount);

}
