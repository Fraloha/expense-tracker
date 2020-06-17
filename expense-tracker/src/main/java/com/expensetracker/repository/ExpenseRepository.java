package com.expensetracker.repository;
import com.expensetracker.model.Category;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpensesGreaterThanAmount(final BigDecimal amount);
    List<Expense> findExpensesByCategory(final Category cat);
    List<Expense> findExpenseByUser(final User u);
}