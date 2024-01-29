package br.com.danilochaves.catalogproject.repositories;

import br.com.danilochaves.catalogproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
