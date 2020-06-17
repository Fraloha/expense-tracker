package com.expensetracker.dto;
import java.math.BigDecimal;
import java.util.Map;
import lombok.Data;

@Data
public class StatsDTOs {

    private String month;
    private BigDecimal total;
    private Map<String, BigDecimal> byCategory;

    public StatsDTOs(String m, BigDecimal t, Map<String, BigDecimal> byCat) {
        this.month = m;
        this.total = t;
        this.byCategory = byCat;
    }
    
}
