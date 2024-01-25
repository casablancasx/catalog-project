package br.com.danilochaves.catalogproject.services;

import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.repositories.CategoryRepository;


import br.com.danilochaves.catalogproject.services.exceptions.ResourceAlreadyExistException;
import br.com.danilochaves.catalogproject.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public CategoryDTO insert(CategoryDTO dto) {
        Optional<Category> optinal = repository.findByName(dto.getName());
        if (optinal.isPresent()){
            throw new ResourceAlreadyExistException("Entity already exist!");
        }
        Category entity = new Category();
        entity.setName(dto.getName());
        entity.setCreatedAt(Instant.now());
        repository.save(entity);
        return new CategoryDTO(entity);
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The entity cannot be updated because the id" +"("+ id+")" + " was not found!"));
        entity.setName(dto.getName());
        entity.setUpdatedAt(Instant.now());
        repository.save(entity);
        return new CategoryDTO(entity);
    }


}
