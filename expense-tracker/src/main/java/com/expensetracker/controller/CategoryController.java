package com.expensetracker.controller;

import com.expensetracker.dto.CategoryDTOs.*;
import com.expensetracker.model.Category;
import com.expensetracker.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName(), c.getDescription()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            return ResponseEntity.badRequest().body("Categoria già esistente");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Category saved = categoryRepository.save(category);
        return ResponseEntity.ok(new CategoryResponse(saved.getId(), saved.getName(), saved.getDescription()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
