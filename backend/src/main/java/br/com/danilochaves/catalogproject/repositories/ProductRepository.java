package br.com.danilochaves.catalogproject.repositories;

import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}