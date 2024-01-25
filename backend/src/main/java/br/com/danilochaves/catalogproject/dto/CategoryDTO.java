package br.com.danilochaves.catalogproject.dto;

import br.com.danilochaves.catalogproject.entities.Category;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CategoryDTO {
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
    }
}
