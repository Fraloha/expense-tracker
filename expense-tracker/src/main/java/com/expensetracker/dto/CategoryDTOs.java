import com.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public class CategoryDTOs {

    @Data
    public static class CategoryRequest {

        @NotBlank
        private String name;
        private String description;
    }

    @Data
    public static class CategoryResponse {
        private Long id;
        private String name;
        private String description;

        public CategoryResponse(Long id, String name, String desc) {
            this.id =  id;
            this.name = name;
            this.description = desc;
        }
    }
}
