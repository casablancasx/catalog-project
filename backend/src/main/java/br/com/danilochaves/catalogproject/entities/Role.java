package br.com.danilochaves.catalogproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
}
