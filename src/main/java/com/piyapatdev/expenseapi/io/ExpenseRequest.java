package com.piyapatdev.expenseapi.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank(message = "กรุณาระบุชื่อของค่าใช้จ่าย")
    @Size(min = 3,message = "ชื่อของค่าใช้จ่ายต้องมีตัวอักษรอย่างน้อย 3 ตัว")
    private String name;

    private String note;

    @NotBlank(message = "กรุณาระบุประเภทของค่าใช้จ่าย")
    private String category;

    @NotNull(message = "กรุณาระบุวันที่ของค่าใช้จ่าย")
    private Date date;

    @NotNull(message = "กรุณาระบุจำนวนของค่าใช้จ่าย")
    private BigDecimal amount;

}
