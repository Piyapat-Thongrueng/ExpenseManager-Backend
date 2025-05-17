package com.piyapatdev.expenseapi.service;

import com.piyapatdev.expenseapi.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 * @author PiyapatDev
 * */
public interface ExpenseService {

    /**
     * It will fetch the expenses from the database
     * @return list
     * */
    List<ExpenseDTO> getAllExpenses();

    /**
     * It will fetch the single expense details from the database
     * @param expenseId
     * @return ExpenseDTO
     * */
    ExpenseDTO getExpenseByExpenseId(String expenseId);
}
