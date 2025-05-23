package com.piyapatdev.expenseapi.controller;

import com.piyapatdev.expenseapi.dto.ExpenseDTO;
import com.piyapatdev.expenseapi.io.ExpenseRequest;
import com.piyapatdev.expenseapi.io.ExpenseResponse;
import com.piyapatdev.expenseapi.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is controller class for Expense module
 * @author PiyapatDev
 * */

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;
    /**
     * It will fetch the expenses from the database
     * @return list
     * */
    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");
        //Call the service method
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Printing the data from service {}", list);
        //Convert the ExpenseDTO to ExpenseResponse
        List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO))
                .collect(Collectors.toList());
        //Return the list/response
        return response;
    }
    /**
     * It will fetch the single expense from the database
     * @param expenseId
     * @return ExpenseResponse
     * */
    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        log.info("API GET /expenses/{} called", expenseId);
        ExpenseDTO expenseDTO = expenseService.getExpenseByExpenseId(expenseId);
        log.info("Printing the expense details {}", expenseId);
        return mapToExpenseResponse(expenseDTO);
    }
    /**
     * It will delete the expense from the database
     * @param expenseId
     * @return void
     * */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{expenseId}")
    public void deleteExpenseByExpenseId(@PathVariable String expenseId) {
        log.info("API DELETE /expenses/{} called", expenseId);
        expenseService.deleteExpenseByExpenseId(expenseId);
    }
    /**
     * It will save the expense details to the database
     * @param expenseRequest
     * @return ExpenseResponse
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expenses")
    public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest) {
        log.info("API POST /expenses called {}", expenseRequest);
        ExpenseDTO expenseDTO = mapToExpenseDTO(expenseRequest);
        expenseDTO = expenseService.saveExpenseDetails(expenseDTO);
        log.info("Printing the expense dto{}", expenseDTO);
        return mapToExpenseResponse(expenseDTO);
    }
    /**
     * It will update the expense details to the database
     * @param updateRequest
     * @param expenseId
     * @return ExpenseResponse
     * */
    @PutMapping("/expenses/{expenseId}")
    public ExpenseResponse updateExpenseDetails(@RequestBody ExpenseRequest updateRequest, @PathVariable String expenseId) {
        log.info("API PUT /expenses/{} request body {}", expenseId, updateRequest);
        ExpenseDTO updatedExpenseDTO = mapToExpenseDTO(updateRequest);
        updatedExpenseDTO = expenseService.updateExpenseDetails(updatedExpenseDTO, expenseId);
        log.info("Printing the updated expense details {}", updatedExpenseDTO);
        return mapToExpenseResponse(updatedExpenseDTO);
    }








    /**
     * Mapper method to map values from Expense request to expense dto
     * @param expenseRequest
     * @return ExpenseDTO
     * */
    private ExpenseDTO mapToExpenseDTO(@Valid ExpenseRequest expenseRequest) {
        return modelMapper.map(expenseRequest, ExpenseDTO.class);
    }
    /**
     * Mapper method for converting expense dto object to expense response
     * @param expenseDTO
     * @return ExpenseResponse
     * */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
