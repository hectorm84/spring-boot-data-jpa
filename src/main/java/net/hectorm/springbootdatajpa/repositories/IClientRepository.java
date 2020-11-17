package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.Client;
import java.util.Set;

public interface IClientRepository {

    Set<Client> findAll();

    Client findById(Long id);

    Set<Client> findByLastName(String lastName);

    Set<Client> findByFirstName(String firstName);

    Client save(Client client);

    void delete(Client client);

    void deleteById(Long id);
}
