package com.piyapatdev.expenseapi.service.impl;

import com.piyapatdev.expenseapi.dto.ExpenseDTO;
import com.piyapatdev.expenseapi.entity.ExpenseEntity;
import com.piyapatdev.expenseapi.exceptions.ResourceNotFoundException;
import com.piyapatdev.expenseapi.repository.ExpenseRepository;
import com.piyapatdev.expenseapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
     * It will fetch the single expense details from the database
     * @param expenseId
     * @return ExpenseDTO
     * */
    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("ขออภัยไม่พบข้อมูลที่คุณค้นหา กรุณาตรวจสอบรายการอีกครั้ง"));
        log.info("Printing the expense entity details {}", expenseEntity);
        return mapToExpenseDTO(expenseEntity);
    }
    /**
     * It will delete the expense from the database
     * @param expenseId
     * @return void
     * */
    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("ขออภัยไม่พบข้อมูลที่คุณค้นหา กรุณาตรวจสอบรายการอีกครั้ง"));
        log.info("Printing the expense entity {}", expenseEntity);
        expenseRepository.delete(expenseEntity);
    }
    /**
     * It will save the expense details to the database
     * @param expenseDTO
     * @return ExpenseDTO
     * */
    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseEntity = maptoExpenseEntity(expenseDTO);
        newExpenseEntity.setExpenseId(UUID.randomUUID().toString());
        newExpenseEntity = expenseRepository.save(newExpenseEntity);
        log.info("Printing the new expense entity details {}", newExpenseEntity);
        return mapToExpenseDTO(newExpenseEntity);
    }









    /**
     * Mapper method to map values from ExpenseDTO to ExpenseEntity
     * @param expenseDTO
     * @return ExpenseEntity
     * */
    private ExpenseEntity maptoExpenseEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseEntity.class);
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
