package com.piyapatdev.expenseapi.repository;

import com.piyapatdev.expenseapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * JPA repository for Expense resource
 * @author PiyapatDev
 * */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    /**
     * It will find the single expense from the database
     * @param expenseId
     * @return Optional
     * */
    Optional<ExpenseEntity> findByExpenseId(String expenseId);

}
