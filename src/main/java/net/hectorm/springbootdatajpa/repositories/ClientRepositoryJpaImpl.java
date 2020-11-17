package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ClientRepositoryJpaImpl implements IClientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public ClientRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT C FROM Client C", Client.class);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Client findById(Long id) {
        Client client = entityManager.find(Client.class, id);
        Optional<Client> op = Optional.of(client);
        return op.get();
    }

    @Override
    public Set<Client> findByLastName(String lastName) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT C FROM Client C WHERE C.lastName = :lname", Client.class)
                                    .setParameter("lname", lastName);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Client> findByFirstName(String firstName) {
        TypedQuery<Client> query = entityManager.createNamedQuery("Client.findByFirstName", Client.class)
                .setParameter("fname", firstName);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Client save(Client client) {
        if(client.getId()!=null && client.getId() >0){
            entityManager.merge(client);
        }else{
            entityManager.persist(client);
        }
        return client;
    }

    @Override
    public void delete(Client client) {
        entityManager.remove(client);

    }

    @Override
    public void deleteById(Long id) {
         Query query = entityManager.createQuery("DELETE  FROM Client C WHERE C.id = ?1")
                .setParameter(1, id);
        int result =  query.executeUpdate();

    }
}
