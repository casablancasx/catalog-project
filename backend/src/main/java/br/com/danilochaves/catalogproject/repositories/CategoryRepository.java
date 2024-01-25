package br.com.danilochaves.catalogproject.repositories;

import br.com.danilochaves.catalogproject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
