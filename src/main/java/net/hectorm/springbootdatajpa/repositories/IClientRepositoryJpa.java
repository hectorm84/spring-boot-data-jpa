package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IClientRepositoryJpa extends JpaRepository<Client, Long> {

    public Set<Client> findByLastName(String lastName);

    public Set<Client> findByFirstName(String lastName);
}
