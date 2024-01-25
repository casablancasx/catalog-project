package br.com.danilochaves.catalogproject.services;

import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import br.com.danilochaves.catalogproject.dto.ProductDTO;
import br.com.danilochaves.catalogproject.entities.Category;
import br.com.danilochaves.catalogproject.entities.Product;
import br.com.danilochaves.catalogproject.repositories.CategoryRepository;
import br.com.danilochaves.catalogproject.repositories.ProductRepository;
import br.com.danilochaves.catalogproject.services.exceptions.ResourceAlreadyExistException;
import br.com.danilochaves.catalogproject.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> listEntity = repository.findAll(pageable);
        Page<ProductDTO> listToDTO = listEntity.map((cat) -> new ProductDTO(cat));
        return listToDTO;
    }
    @Transactional
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        Product entity = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Product not found, id:" + id));
        return new ProductDTO(entity,entity.getCategories());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Optional<Product> optinal = repository.findByName(dto.getName());
        if (optinal.isPresent()){
            throw new ResourceAlreadyExistException("Product already exist!");
        }
        Product entity = new Product();
        copyDtoToEntity(dto,entity);

        repository.save(entity);
        return new ProductDTO(entity,entity.getCategories());
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The product cannot be updated because the id" +"("+ id+")" + " was not found!"));
        copyDtoToEntity(dto,entity);
        repository.save(entity);
        return new ProductDTO(entity,entity.getCategories());
    }


    public void delete(Long id) {
        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
        repository.delete(entity);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto: dto.getCategories()) {
            Category category = categoryRepository.findById(catDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Category not exist"));
            entity.getCategories().add(category);
        }

    }
}
