package br.com.danilochaves.catalogproject.services;

import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.repositories.CategoryRepository;


import br.com.danilochaves.catalogproject.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> listEntity = repository.findAll(pageable);
        Page<CategoryDTO> listToDTO = listEntity.map((cat) -> new CategoryDTO(cat));
        return listToDTO;
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        Category entity = optionalCategory.orElseThrow(() -> new ResourceNotFoundException("Entity not found, id:" + id));
        return new CategoryDTO(entity);
    }
}
