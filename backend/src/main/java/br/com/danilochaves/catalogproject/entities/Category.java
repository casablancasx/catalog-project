package br.com.danilochaves.catalogproject.entities;

import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category(CategoryDTO dto){
        id = dto.getId();
        name = dto.getName();
        setCreatedAt(Instant.now());
    }
}
