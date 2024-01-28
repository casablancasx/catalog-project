package br.com.danilochaves.catalogproject.dto;

import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Positive
    private Double price;
    @PastOrPresent
    private Instant date;
    private String imgUrl;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        date = entity.getDate();
        imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }
}

