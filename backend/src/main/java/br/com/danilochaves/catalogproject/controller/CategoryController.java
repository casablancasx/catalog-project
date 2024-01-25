package br.com.danilochaves.catalogproject.controller;


import br.com.danilochaves.catalogproject.dto.CategoryDTO;
import br.com.danilochaves.catalogproject.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable){
        Page<CategoryDTO> listDTO = service.findAllPaged(pageable);
        return ResponseEntity.ok(listDTO);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO objDTO = service.findById(id);
        return ResponseEntity.ok(objDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.created(URI.create("/categories/" + dto.getId())).body(dto);
    }
}
