package br.com.danilochaves.catalogproject.dto;

import br.com.danilochaves.catalogproject.entities.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    private List<CategoryDTO> categoryDTOS = new ArrayList<>();

    public ProductDTO(Product entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        entity.getCategories().forEach((category -> categoryDTOS.add(new CategoryDTO(category))));
    }
}
