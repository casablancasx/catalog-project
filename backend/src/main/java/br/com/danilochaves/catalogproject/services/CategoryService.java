package br.com.danilochaves.catalogproject.services;

import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> listEntity = repository.findAll(pageable);
        Page<CategoryDTO> listToDTO = listEntity.map((cat) -> new CategoryDTO(cat));
        return listToDTO;
    }
}
