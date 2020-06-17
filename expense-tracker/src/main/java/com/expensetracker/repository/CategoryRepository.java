package com.expensetracker.repository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<List<Expense>> findExpensesByCategory(String name);
    boolean existsCategory(String name);
    boolean isCategoryEmpty(String name);
}
