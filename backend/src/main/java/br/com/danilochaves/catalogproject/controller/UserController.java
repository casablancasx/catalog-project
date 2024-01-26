package br.com.danilochaves.catalogproject.controller;



import br.com.danilochaves.catalogproject.dto.UserDTO;
import br.com.danilochaves.catalogproject.dto.UserInsertDTO;
import br.com.danilochaves.catalogproject.entities.User;
import br.com.danilochaves.catalogproject.services.ProductService;
import br.com.danilochaves.catalogproject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<UserDTO> listDTO = service.findAllPaged(pageable);
        return ResponseEntity.ok(listDTO);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO objDTO = service.findById(id);
        return ResponseEntity.ok(objDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto){
        UserDTO userDTO = service.insert(dto);
        return ResponseEntity.created(URI.create("/products/" + dto.getId())).body(userDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,@Valid @RequestBody UserDTO dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
