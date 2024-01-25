package br.com.danilochaves.catalogproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "tb_category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 20)
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
}
