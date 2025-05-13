package com.piyapatdev.expenseapi.repository;

import com.piyapatdev.expenseapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

}
