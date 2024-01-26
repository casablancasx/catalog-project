package br.com.danilochaves.catalogproject.repositories;

import br.com.danilochaves.catalogproject.entities.Role;
import br.com.danilochaves.catalogproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
