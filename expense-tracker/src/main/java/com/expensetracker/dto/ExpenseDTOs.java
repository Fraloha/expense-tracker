package com.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDTOs {

    @Data
    public static class ExpenseRequest {
        @NotBlank
        private String description;

        @NotNull
        @Positive
        private BigDecimal amount;

        @NotNull
        private LocalDate date;

        private Long categoryId;
    }

    @Data
    public static class ExpenseResponse {
        private Long id;
        private String description;
        private BigDecimal amount;
        private LocalDate date;
        private String categoryName;

        public ExpenseResponse(Long id, String description, BigDecimal amount,
                               LocalDate date, String categoryName) {
            this.id = id;
            this.description = description;
            this.amount = amount;
            this.date = date;
            this.categoryName = categoryName;
        }
    }
    
}