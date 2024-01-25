package br.com.danilochaves.catalogproject.dto;

import br.com.danilochaves.catalogproject.entities.Category;
import lombok.Data;

import java.time.Instant;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Instant createdAt;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
    }
}
