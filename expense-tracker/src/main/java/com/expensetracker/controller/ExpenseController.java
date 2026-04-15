package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseDTOs.*;
import com.expensetracker.dto.StatsDTO;
import com.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // ── Expenses ──────────────────────────────────────────────────────────────

    @GetMapping("/expenses")
    public List<ExpenseResponse> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String month) {
        return expenseService.getAll(category, month);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(expenseService.create(request));
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<ExpenseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {
        try {
            return ResponseEntity.ok(expenseService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            expenseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Stats ─────────────────────────────────────────────────────────────────

    @GetMapping("/stats/monthly")
    public ResponseEntity<StatsDTO> monthlyStats(
            @RequestParam(required = false) String month) {
        return ResponseEntity.ok(expenseService.getMonthlyStats(month));
    }
}
