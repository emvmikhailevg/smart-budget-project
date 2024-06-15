package ru.emelianov.smartbudgetproject.mapper;

import org.mapstruct.*;
import ru.emelianov.smartbudgetproject.database.model.Transaction;
import ru.emelianov.smartbudgetproject.dto.TransactionDTO;

/**
 * <p>Маппер для преобразования объектов {@link Transaction} в объекты {@link TransactionDTO} и наоборот</p>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class)
public interface TransactionMapper {

    /**
     * Преобразует объект {@link Transaction} в объект {@link TransactionDTO}
     *
     * @param transaction объект транзакции
     * @return объект DTO транзакции
     */
    @Mappings({
            @Mapping(source = "bankAccount.id", target = "bankAccountId")
    })
    TransactionDTO convertToDTO(Transaction transaction);

}
