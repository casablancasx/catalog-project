package br.com.danilochaves.catalogproject.entities;


import lombok.*;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private Instant date;
    private String imgUrl;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
}
