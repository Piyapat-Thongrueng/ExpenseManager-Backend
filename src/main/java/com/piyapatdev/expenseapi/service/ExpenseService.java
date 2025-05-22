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
    /**
     * It will delete the expense from the database
     * @param expenseId
     * @return void
     * */
    void deleteExpenseByExpenseId(String expenseId);
    /**
     * It will save the expense details to the database
     * @param expenseDTO
     * @return ExpenseDTO
     * */
    ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);
    /**
     * It will update the expense details to the database
     * @param expenseDTO
     * @param expenseId
     * @return ExpenseDTO
     * */
    ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId);
}
