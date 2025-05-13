package com.piyapatdev.expenseapi.service.impl;

import com.piyapatdev.expenseapi.dto.ExpenseDTO;
import com.piyapatdev.expenseapi.entity.ExpenseEntity;
import com.piyapatdev.expenseapi.repository.ExpenseRepository;
import com.piyapatdev.expenseapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Expense module
 * @author PiyapatDev
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    /**
     * It will fetch the expenses from the database
     * @return list
     * */
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        //Call the repository method
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing the data from repository {}", list);
        //Convert the Entity object to DTO's object
        List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity))
                .collect(Collectors.toList());
        return listOfExpenses;
    }

    /**
     * Mapper method to convert expenses entity to expenseDTO
     * @param expenseEntity
     * @return ExpenseDTO
     * */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity,ExpenseDTO.class);
    }

}
