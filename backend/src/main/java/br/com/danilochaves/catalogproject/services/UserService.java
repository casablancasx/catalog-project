package br.com.danilochaves.catalogproject.services;


import br.com.danilochaves.catalogproject.dto.RoleDTO;
import br.com.danilochaves.catalogproject.dto.UserDTO;
import br.com.danilochaves.catalogproject.dto.UserInsertDTO;

import br.com.danilochaves.catalogproject.entities.Role;
import br.com.danilochaves.catalogproject.entities.User;

import br.com.danilochaves.catalogproject.repositories.RoleRepository;
import br.com.danilochaves.catalogproject.repositories.UserRepository;

import br.com.danilochaves.catalogproject.services.exceptions.ResourceAlreadyExistException;
import br.com.danilochaves.catalogproject.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

   
    @Transactional
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> listEntity = repository.findAll(pageable);
        Page<UserDTO> listToDTO = listEntity.map((cat) -> new UserDTO(cat));
        return listToDTO;
    }
    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        User entity = optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found, id:" + id));
        return new UserDTO(entity);
    }
    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        Optional<User> optinal = repository.findByEmail(dto.getEmail());
        if (optinal.isPresent()){
            throw new ResourceAlreadyExistException("User already exist!");
        }
        User entity = new User();
        copyDtoToEntity(dto,entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(entity);
        return new UserDTO(entity);
    }
    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        User entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The User cannot be updated because the id("+id+") was not found!"));
        copyDtoToEntity(dto,entity);
        repository.save(entity);
        return new UserDTO(entity);
    }

    public void delete(Long id) {
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
        repository.delete(entity);
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for (RoleDTO rolDTO: dto.getRoles()) {
            Role role = roleRepository.findById(rolDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Category not exist"));
            entity.getRoles().add(role);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).get();
        if (user == null){
            logger.error("User noy found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return user;
    }
}
